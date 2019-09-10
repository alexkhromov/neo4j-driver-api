:param names => ['Jennifer','Michelle','Tanya','Julie','Christie','Sophie','Amanda','Khloe','Sarah','Kaylee'];

FOREACH (i IN range(1, size($names)) |
  CREATE (f1:Friend{name:$names[i % size($names)] + i})
  FOREACH (j IN range(1, size($names)) |
	  CREATE (f1) - [:FRIEND_OF] -> (f2:Friend{name:$names[j % size($names)] + i + '-' + i})
    FOREACH (k IN range(1, size($names)) |
      CREATE (f2) - [:FRIEND_OF] -> (f3:Friend{name:$names[k % size($names)] + i + '-' + i + '-' + i})
      FOREACH (l IN range(1, size($names)) |
        CREATE (f3) - [:FRIEND_OF] -> (f4:Friend{name:$names[l % size($names)] + i + '-' + i + '-' + i + '-' + i})
        FOREACH (m IN range(1, size($names)) |
          CREATE (f4) - [:FRIEND_OF] -> (f5:Friend{name:$names[m % size($names)] + i + '-' + i + '-' + i + '-' + i + '-' + i})
          FOREACH (n IN range(1, size($names)) |
            CREATE (f5) - [:FRIEND_OF] -> (f6:Friend{name:$names[n % size($names)] + i + '-' + i + '-' + i + '-' + i + '-' + i + '-' + i})
          )
        )
      )
    )
  )
);

MATCH (f1:Friend), (f2:Friend)
WHERE f1.name =~ '\\D+\\d+' AND f2.name =~ '\\D+\\d+'
AND f1.name <> f2.name AND id(f1) < id(f2)
CREATE (f1) - [:FRIEND_OF] -> (f2);

MATCH (ff1:Friend) - [:FRIEND_OF] - (f), (ff2:Friend) - [:FRIEND_OF] - (f)
WHERE ff1.name =~ '\\D+\\d+-\\d+' AND ff2.name =~ '\\D+\\d+-\\d+'
AND ff1.name <> ff2.name AND id(ff1) < id(ff2)
CREATE (ff1) - [:FRIEND_OF] -> (ff2);

MATCH (fff1:Friend) - [:FRIEND_OF] - (f), (fff2:Friend) - [:FRIEND_OF] - (f)
WHERE fff1.name =~ '\\D+\\d+-\\d+-\\d+' AND fff2.name =~ '\\D+\\d+-\\d+-\\d+'
AND fff1.name <> fff2.name AND id(fff1) < id(fff2)
CREATE (fff1) - [:FRIEND_OF] -> (fff2);

MATCH (ffff1:Friend) - [:FRIEND_OF] - (f), (ffff2:Friend) - [:FRIEND_OF] - (f)
WHERE ffff1.name =~ '\\D+\\d+-\\d+-\\d+-\\d+' AND ffff2.name =~ '\\D+\\d+-\\d+-\\d+-\\d+'
AND ffff1.name <> ffff2.name AND id(ffff1) < id(ffff2)
CREATE (ffff1) - [:FRIEND_OF] -> (ffff2);

MATCH (fffff1:Friend) - [:FRIEND_OF] - (f), (fffff2:Friend) - [:FRIEND_OF] - (f)
WHERE fffff1.name =~ '\\D+\\d+-\\d+-\\d+-\\d+-\\d+' AND fffff2.name =~ '\\D+\\d+-\\d+-\\d+-\\d+-\\d+'
AND fffff1.name <> fffff2.name AND id(fffff1) < id(fffff2)
CREATE (fffff1) - [:FRIEND_OF] -> (fffff2);