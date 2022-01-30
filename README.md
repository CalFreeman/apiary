## Launch postgres server via docker-compose
$ cd /apiary/docker/postgres
$ docker-compose up -d

> Recreating postgres_postgres_1 ... done

## Find postgres container id &/or name 
[@localhost postgres]$ docker ps
> CONTAINER ID   IMAGE      COMMAND                  CREATED          STATUS          PORTS                                       NAMES
> 26a6e42130f2   postgres   "docker-entrypoint.s…"   18 seconds ago   Up 16 seconds   0.0.0.0:5432->5432/tcp, :::5432->5432/tcp   postgres_postgres_1
## access container shell
[@localhost apiary]$ docker exec -it postgres_postgres_1 bash
root@481b504d9316:/# psql -U postgres
### create user and database
> $su - postgres
> 
> $createuser --interactive --pwprompt
> 
> $su - postgres
>
> $createdb -O myuser testdb

## using psql log into testdb with myuser
root@26a6e42130f2:/# psql -d testdb -U myuser

---
## healthcheck
$ curl localhost:8080/actuator/health

+hive
  +id
  +Nickname
  +Assessment
    +found eggs/larva/cappedbrood/all, present?
    +found brood/honey/pollen, brood pattern?
    +Manipulation Notes
      +Evaulation
        +nosema  	 1poor-5excellent
        +growth  	 1poor-5excellent
        +vigour 	 1slow-5strong
        +temper  	 1hot-5calm
        ---
        +winter cluster  1-10
        +spring cluster  1-10
        +spring Reserves 1-10
        +summer cluster  1-10
        +hygenic  	 1-5
  +Feed
    +type
      +pollen/protein
        +quantity
      +syrup/sugar/hfcs
        +quantity
  +Queen
    +Status
      +LQ(LayinQueen)/NQ(NewQueen)/CQC(cappedQueenCells)
        +#CQC/date
    +QueenName(<apiaryLocation>+<queenId>)
    +QueenSpotted?
      +marked?
        +color/year
    +Origin
      +requeened date/supplier
  +Medication
    +type
      +applicationDate
    +miteCount
      +varroaSpring
      +varroaFall
  +Observations/notes
  +Harvest
    +dates of honey pulled
    +how much pulled

  
  ---
  postgres=# DROP DATABASE testdb;
  postgres=# CREATE DATABASE testdb OWNER myuser;
  postgres-# \l
  postgres=# /c testdb
  testdb-# \d apiarys

  SELECT 
    table_name, 
    column_name, 
    data_type 
  FROM 
     information_schema.columns
  WHERE 
     table_name = 'farms';
  
  testdb=# TABLE apiarys;

  
//DATABASE
POST:
http://localhost:8080/apiarys
{
  "name": "apyName1",
  "location": "apyloc1",
    "farm":{
      "id": "1"
    }
}