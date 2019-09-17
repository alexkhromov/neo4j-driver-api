1. Download and install Neo4j community server<br>
   https://neo4j.com/download-center/#community
   
2. Optional - install neo4j as a service

3. After installation server will be available at http://localhost:7474<br>
   On first login you will be prompt to change default password
   
4. Download and put into NEO4J_HOME\plugins apoc library ( Awesome Procedures On Cypher )<br>
   https://github.com/neo4j-contrib/neo4j-apoc-procedures#version-compatibility-matrix
   
5. Update NEO4J_HOME\conf\neo4j.conf
   
   uncomment if needed:<br>
   dbms.directories.plugins=plugins
   
   uncomment if needed and set values according your environment:<br>
   dbms.memory.heap.initial_size=2048m<br>
   dbms.memory.heap.max_size=4096m
   
   add:<br>
   dbms.security.procedures.whitelist=apoc.*<br>
   apoc.export.file.enabled=true
   
6. Optional - if neo4j installed as a service run command when server is stopped<br>
   neo4j update-service
   
7. Use cypher scripts from folder _neo4j-search/script_ to create or cleanup nodes and relationships

   for Windows<br>
   type <path-to>\mn_create.cypher | cypher-shell -u <username> -p <password> --format plain<br>
   type <path-to>\mn_cleanup.cypher | cypher-shell -u <username> -p <password> --format plain
   
8. Optional - copy _runConfigurations_ folder into _.idea_ folder in case of Intellij Idea usage

   https://drive.google.com/file/d/1UF0hf1I6UeLX7SE_qXhpFORh7rjD7hTW/view?usp=sharing