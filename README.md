###$ Launch postgres server via docker-compose
> $ cd /apiary/docker/postgres  
$ docker-compose up -d

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
> ###$ Alternate DB Creation //why is this here?
>postgres=# DROP DATABASE testdb;  
postgres=# CREATE DATABASE testdb OWNER myuser;  
postgres-# \l //List  
postgres=# /c testdb  
testdb-# \d apiarys  
>#### pragmatic way of displaying table info
>    >SELECT  
>    table_name,  
>    column_name,  
>    data_type  
>    FROM  
>    information_schema.columns  
>    WHERE  
>    table_name = 'farms';  
> ###List rows?
>       testdb=# TABLE apiarys;