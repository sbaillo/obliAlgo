use obligatorio2018

/*Trigger A*/
/*ANDA*/
/*Cada vez que se ingrese un partido, se actualicen los 
partidos ganados y perdidos según corresponda en los datos del jugador.*/
create trigger actualizarPartidos
on partidos
after insert
as
begin
	update jugadores
	set partidosGanados = partidosGanados + Ganados
	from (select jugadorGanador, COUNT(jugadorGanador) as 'Ganados'
		  from inserted
		  group by jugadorGanador)tablaGanador
	where jugadores.jugadorID = tablaGanador.jugadorGanador


	update jugadores
	set partidosPerdidos = partidosPerdidos + Perdidos
	from   (select jugadorPerdedor, COUNT(jugadorPerdedor) as 'Perdidos'
	 	   from inserted
		   group by jugadorPerdedor)tablaPerdedor
	where jugadores.jugadorID = tablaPerdedor.jugadorPerdedor 

end


/*NO ANDA ------- no hace el insert en la tabla logs pero si hace el insert de los
ranking*/
/*Trigger c*/
/*Cuando se cree un nuevo ranking, se debe generar un log en el que se deberá registrar
para cada jugador del top ten: si mantiene la posición, si modifica la posición, o
si deja de estar en el top 10. Implementar la estructura necesaria para poder
soportar este disparador.*/

CREATE TABLE rankinglog 
  ( 
     fecharanking INT NOT NULL, 
     jugadorid    INT NOT NULL, 
     mantiene     BIT, 
     modifica     BIT, 
     dejatop10    BIT 
  ); 

CREATE TRIGGER logsderanking 
ON ranking 
instead OF INSERT 
AS 
  BEGIN 
      INSERT INTO rankinglog 
      SELECT rankingNuevo.fecharanking, 
             rankingViejo.jugadorid, 
             CASE 
               WHEN ( rankingNuevo.posicion = rankingViejo.posicion ) THEN 1 
               ELSE 0 
             END, 
             CASE 
               WHEN ( rankingNuevo.posicion <> rankingViejo.posicion ) THEN 1 
               ELSE 0 
             END, 
             CASE 
               WHEN ( rankingNuevo.posicion > 10 ) THEN 1 
               ELSE 0 
             END 
      FROM   /*Ranking insertado*/ 
      (SELECT posicion, 
              jugadorid, 
              fecharanking 
       FROM   inserted)rankingNuevo, 
      /*Top 10 actual*/ 
      (SELECT posicion, 
              jugadorid 
       FROM   ranking 
       WHERE  posicion <= 10 
              AND fecharanking = (SELECT Max(fecharankingid) 
                                  FROM   fecharanking))rankingViejo 
      WHERE  rankingNuevo.jugadorid = rankingViejo.jugadorid 

      INSERT INTO ranking 
      SELECT * 
      FROM   inserted 
  END 


/*ANDA ----- CONTROLAR EL Año de la week. PREGUNTAR AL PROFE*/
/*Trigger E*/
/*Controlar que no se ingrese más de un ranking por semana (controlar el ingreso
y modificación de la fecha en FechaRanking)*/

CREATE TRIGGER controlfecharanking 
ON fecharanking 
instead OF INSERT, UPDATE 
AS 
  BEGIN 
      IF EXISTS (SELECT * 
                 FROM   inserted) 
         AND NOT EXISTS (SELECT * 
                         FROM   deleted) 
        BEGIN 
            INSERT INTO fecharanking 
            SELECT fecha 
            FROM   inserted 
            WHERE  Str(Datepart(week, inserted.fecha)) + ':' 
                   + Str(Datepart(year, inserted.fecha)) NOT IN (SELECT 
                          Str(Datepart(week, fecharanking.fecha)) 
                          + ':' 
                          + Str(Datepart(year, fecharanking.fecha)) 
                                                                 FROM 
                   fecharanking) 
        END 

      IF EXISTS (SELECT * 
                 FROM   inserted) 
         AND EXISTS (SELECT * 
                     FROM   deleted) 
        BEGIN 
            UPDATE fecharanking 
            SET    fecha = inserted.fecha 
            FROM   inserted 
            WHERE  fecharanking.fecharankingid = inserted.fecharankingid 
                   AND Str(Datepart(week, inserted.fecha)) + ':' 
                       + Str(Datepart(year, inserted.fecha)) NOT IN 
                       (SELECT 
                           Str(Datepart(week, fecharanking.fecha)) 
                           + ':' 
                           + Str(Datepart(year, fecharanking.fecha)) 
                                                                     FROM 
                       fecharanking 
                       ) 
        END 
  END 



/*ANDA OK*/
/*Trigger G*/
/*Implementar un disparador que al eliminar un torneo
elimine todo lo relacionado al mismo (edición, partidos).*/

CREATE TRIGGER eliminartorneo 
ON torneos 
instead OF DELETE 
AS 
  BEGIN 
      DELETE partidos 
      WHERE  partidos.torneoid IN (SELECT torneoid 
                                   FROM   deleted) 

      DELETE torneoedicion 
      WHERE  torneoedicion.torneoid IN (SELECT torneoid 
                                        FROM   deleted) 

      DELETE torneos 
      WHERE  torneos.torneoid IN (SELECT torneoid 
                                  FROM   deleted) 
  END 