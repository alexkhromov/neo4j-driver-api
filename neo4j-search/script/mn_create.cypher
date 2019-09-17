FOREACH (c IN range(1, 10) |
		CREATE (controller:Controller{name:"MSC" + c})
		FOREACH (l IN range(1, 5) |
				CREATE (location:LocationArea{name:"LA" + l})
				CREATE (controller) - [cl:HAS_LOCATION] -> (location)
				FOREACH (ce IN range(1, 1700) |
						CREATE (cell:Cell{name:"Cell" + ce})
						CREATE (location) - [lc:HAS_CELL] -> (cell)
						FOREACH (s IN range(1, 12) |
								CREATE (sector:Sector{name:"Sector" + s})
								CREATE (cell) - [cs:HAS_SECTOR] -> (sector)
								FOREACH (ignoreMe IN CASE WHEN s >= 1 AND s <= 4 THEN [1] ELSE [] END | SET sector.type = "2G")
								FOREACH (ignoreMe IN CASE WHEN s >= 5 AND s <= 8 THEN [1] ELSE [] END | SET sector.type = "3G")
								FOREACH (ignoreMe IN CASE WHEN s >= 9 AND s <= 12 THEN [1] ELSE [] END | SET sector.type = "4G")
								FOREACH (ignoreMe IN CASE WHEN s % 4 = 1 THEN [1] ELSE [] END | SET sector.azimuth = "0")
								FOREACH (ignoreMe IN CASE WHEN s % 4 = 2 THEN [1] ELSE [] END | SET sector.azimuth = "90")
								FOREACH (ignoreMe IN CASE WHEN s % 4 = 3 THEN [1] ELSE [] END | SET sector.azimuth = "180")
								FOREACH (ignoreMe IN CASE WHEN s % 4 = 0 THEN [1] ELSE [] END | SET sector.azimuth = "270")
						)
				)
		)
);