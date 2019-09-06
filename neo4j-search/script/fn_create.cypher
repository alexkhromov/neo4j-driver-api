:param names => ['Jennifer', 'Michelle', 'Tanya'];

FOREACH (i IN range(1, size($names)) |
  CREATE (f:Friend{name:$names[i % size($names)] + i})
  FOREACH (j IN range(1, size($names)) |
	  CREATE (f) - [:FRIEND_OF] -> (ff:Friend{name:$names[j % size($names)] + i + '-' + i})
  )
);

MATCH (f1:Friend), (f2:Friend)
WHERE f1.name =~ '\\D+\\d{1}' AND f2.name =~ '\\D+\\d{1}'
AND f1.name <> f2.name AND id(f1) < id(f2)
CREATE (f1) - [:FRIEND_OF] -> (f2);

MATCH (ff1:Friend) - [:FRIEND_OF] - (f), (ff2:Friend) - [:FRIEND_OF] - (f)
WHERE ff1.name =~ '\\D+\\d+-\\d+' AND ff2.name =~ '\\D+\\d+-\\d+'
AND ff1.name <> ff2.name AND id(ff1) < id(ff2)
CREATE (ff1) - [:FRIEND_OF] -> (ff2);

UNWIND $names as name
MATCH (ff1:Friend), (ff2:Friend)
WHERE ff1.name =~ '\\D+\\d+-\\d+' AND ff2.name =~ '\\D+\\d+-\\d+'
AND ff1.name STARTS WITH name AND ff2.name STARTS WITH name
AND ff1.name <> ff2.name AND id(ff1) < id(ff2)
CREATE (ff1) - [:FRIEND_OF] -> (ff2);