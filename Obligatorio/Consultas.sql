

/*ANDA -------- REVISAR EL "TODOS"*/
/*Query a*/

SELECT DISTINCT j.jugadorid, 
                j.nombre 
FROM   jugadores j 
WHERE  j.jugadorid IN (SELECT DISTINCT j1.jugadorid 
                       FROM   partidos p, 
                              jugadores j1, 
                              torneos t 
                       WHERE  p.torneoid = t.torneoid 
                              AND t.torneotipo = 'GS' 
                              AND p.anio = Year(Getdate()) 
                              AND ( j1.jugadorid = p.jugadorganador 
                                     OR j1.jugadorid = p.jugadorperdedor ) 
                              AND p.torneoid IN 
                                  /*Grand Slams jugados en 
                                  2018*/ 
                                  (SELECT DISTINCT p.torneoid 
                                   FROM   partidos p, 
                                          torneos t 
                                   WHERE  p.torneoid = t.torneoid 
                                          AND t.torneotipo = 'GS' 
                                          AND p.anio = Year(Getdate()))) 


/*NO ANDA-----------------------------------------------------------------------------------------------*/
/*QUERY C*/


select j.nombre, g.nombre, p.nombre, /*Veces al que gano mas el jugador 1*/
			(select MAX(miTabla.CantidadDeVeces)	
			from (select p.jugadorGanador, p.jugadorPerdedor, count(*) as 'CantidadDeVeces'
                  from Partidos p
                  group by p.jugadorGanador, p.jugadorPerdedor
                  )miTabla	
			where miTabla.jugadorGanador = 1), 

			(/*Veces que le ganaron mas el jugador 1*/
			select MAX(miTabla.CantidadDeVeces)	
			from (select p.jugadorGanador, p.jugadorPerdedor, count(*) as 'CantidadDeVeces'
                  from Partidos p
                  group by p.jugadorGanador, p.jugadorPerdedor
                  )miTabla	
			where miTabla.jugadorPerdedor = 1)



/*ANDA---------------------------------------------------------------------------------------------------*/
/*QUERY E*/

SELECT TOP 1 nombre, 
             totalgrandslamsganados 
FROM   (SELECT nombre, 
               Count(*) 'TotalGrandSlamsGanados' 
        FROM   (SELECT j.jugadorid, 
                       j.nombre 
                FROM   jugadores j, 
                       torneoedicion te, 
                       torneos t 
                WHERE  t.torneoid = te.torneoid 
                       AND t.torneotipo = 'GS' 
                       AND te.jugadorganador = j.jugadorid 
                       AND jugadorid IN (SELECT DISTINCT j1.jugadorid 
                                         FROM   partidos p, 
                                                jugadores j1, 
                                                torneos t 
                                         WHERE  p.torneoid = t.torneoid 
                                                AND p.anio = Year(Getdate()) 
                                                AND ( j1.jugadorid = 
                                                      p.jugadorganador 
                                                       OR j1.jugadorid = 
                                                          p.jugadorperdedor ))) 
               miTabla 
        GROUP  BY nombre)miTabla2 
ORDER  BY totalgrandslamsganados DESC 