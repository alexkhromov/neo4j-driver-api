foreach ( c in range( 1, 10 ) | 
	create ( controller:Controller { name: "MSC" + c } )
	foreach ( l in range( 1, 5 ) |
		create ( location:LocationArea { name: "LA" + l } )
		create ( controller ) - [ cl:HAS_LOCATION ] -> ( location )
		foreach ( ce in range( 1, 1700 ) |
			create ( cell:Cell { name: "Cell" + ce } )
			create ( location ) - [ lc:HAS_CELL ] -> ( cell )
			foreach ( s in range( 1, 12 ) |
				create ( sector:Sector { name: "Sector" + s } )
				create ( cell ) - [ cs:HAS_SECTOR ] -> ( sector )
				foreach ( ignoreMe in case when s >= 1 AND s <= 4 THEN [1] else [] end | set sector.type = "2G" )
				foreach ( ignoreMe in case when s >= 5 AND s <= 8 THEN [1] else [] end | set sector.type = "3G" )
				foreach ( ignoreMe in case when s >= 9 AND s <= 12 THEN [1] else [] end | set sector.type = "4G" )
				foreach ( ignoreMe in case when s % 4 = 1 THEN [1] else [] end | set sector.azimuth = "0" )
				foreach ( ignoreMe in case when s % 4 = 2 THEN [1] else [] end | set sector.azimuth = "90" )
				foreach ( ignoreMe in case when s % 4 = 3 THEN [1] else [] end | set sector.azimuth = "180" )
				foreach ( ignoreMe in case when s % 4 = 0 THEN [1] else [] end | set sector.azimuth = "270" )
			)
		)
	)
);

create index on :Controller(id);
create index on :LocationArea(id);
create index on :Cell(id);
create index on :Sector(id);