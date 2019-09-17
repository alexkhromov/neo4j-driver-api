CALL apoc.periodic.iterate("MATCH (c:Cell) - [cs:HAS_SECTOR] -> (s:Sector) RETURN cs", "DETACH DELETE cs", {batchSize:10000});
CALL apoc.periodic.iterate("MATCH (l:LocationArea) - [lc:HAS_CELL] -> (c:Cell) RETURN lc", "DETACH DELETE lc", {batchSize:10000});
CALL apoc.periodic.iterate("MATCH (c:Controller) - [cl:HAS_LOCATION] -> (l:LocationArea) RETURN cl", "DETACH DELETE cl", {batchSize:10000});
CALL apoc.periodic.iterate("MATCH (c:Controller) RETURN c", "DETACH DELETE c", {batchSize:10000});
CALL apoc.periodic.iterate("MATCH (l:LocationArea) RETURN l", "DETACH DELETE l", {batchSize:10000});
CALL apoc.periodic.iterate("MATCH (c:Cell) RETURN c", "DETACH DELETE c", {batchSize:10000});
CALL apoc.periodic.iterate("MATCH (s:Sector) RETURN s", "DETACH DELETE s", {batchSize:10000});