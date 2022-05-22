### maven to compile and run
> mvn compile
> mvn spring-boot:run

###$ Launch postgres server via docker-compose
> $ cd /apiary/docker/postgres  
$ docker-compose up -d  
>  goto http://localhost:5050/login to access pgadmin ui  
> ### Connecting pgadmin to postgres
> $docker network ls  //find the bridge between postgres   
> 6845bc55d171   postgres_default   bridge    local  
> $ docker network inspect postgres_default  
> //find "Containers":{ 
>   "<container hash>"
>       "Name": "postgres_pgadmin_1", ...  
>       "IPv4Address": "172.18.0.3/16",  
> //grab this IPv4Address for using to connecting pgadmin to postgresql db container
> password of db is root for testing, verify the IPV4 if unable to connect.
###$ Find postgres container id &/or name 
> [@localhost postgres]$ docker ps      
> ` |26a6e42130f2 |  postgres  | "docker-entrypoint.s…"  | 18 seconds ago  | Up 16 seconds  | 0.0.0.0:5432->5432/tcp, :::5432->5432/tcp  | postgres_postgres_1 `
> ###$ access container shell
>       [@localhost apiary]$ docker exec -it postgres_postgres_1 bash
###$ Create user and database
> #### User Creation:
>       root@7ef8dbd0f8d3:/# su - postgres
>       postgres@7ef8dbd0f8d3:~$ createuser -P -s -e myuser
> #### Database creation:
>       postgres@7ef8dbd0f8d3:~$ createdb -O myuser testdb
> ###$ using psql log into testdb with myuser
>       root@26a6e42130f2:/# psql -d testdb -U myuser
> #####Verify tables were created
>       testdb=# \dt
---
###$ healthcheck
> $ curl localhost:8080/actuator/health
---
###$ /api/
>### User Login implementation components
>#### Creating Default Roles for login
> INSERT INTO roles(name) VALUES('ROLE_USER');  
> INSERT INTO roles(name) VALUES('ROLE_ADMIN');
> `auth/signup`  
> {'
>   "name": "testname2",  
>   "username": "testuserName2",  
>   "email": "testuser2@email.com",  
>   "password": "password"  
> }
>
>####Posting new apiary with farm FK_id
>       ####POST:http://localhost:8080/apiarys  
>       {
>        "name": "apyName1",
>        "location": "apyloc1",
>          "farm":{
>            "id": "1"
>          }
>        }
---
> ###$ Alternate DB Creation //programmatically
>postgres=# DROP DATABASE testdb;  
postgres=# CREATE DATABASE testdb OWNER myuser;  
postgres-# \l //List  
postgres=# /c testdb  
testdb-# \d apiarys  
>#### programmatic way of displaying table info
>    >SELECT  
>    table_name,  
>    column_name,  
>    data_type  
>    FROM  
>    information_schema.columns  
>    WHERE  
>    table_name = 'farms';  
> ###for listing rows?
>       testdb=# TABLE apiarys;

---Fresh Linux---
1. Install Docker
2. Install Docker-compose
    https://docs.docker.com/compose/install/
3. Install postgresql
    https://www.postgresql.org/download/linux/redhat/