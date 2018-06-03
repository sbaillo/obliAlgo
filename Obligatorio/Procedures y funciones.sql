
/*Proc A*/
/*OK*/
create procedure torneosGanadosPorTipoYAnio
@anio int, @idJugador int, @GrandSlam int out, @M1000 int out, @M500 int out, @M250 int
out
as
begin
set @GrandSlam = 0
set @M1000 = 0
set @M500 = 0
set @M250 = 0
select @GrandSlam = count(*)
from Torneos t, TorneoEdicion te
where t.torneoId = te.torneoId AND
 te.anio = @anio AND
 te.jugadorGanador = @idJugador AND
 t.torneoTipo = 'GS'
group by t.torneoTipo
select @M1000 = count(*)
from Torneos t, TorneoEdicion te
where t.torneoId = te.torneoId AND
 te.anio = @anio AND
 te.jugadorGanador = @idJugador AND
 t.torneoTipo = '1000'
group by t.torneoTipo
select @M500 = count(*)
from Torneos t, TorneoEdicion te
where t.torneoId = te.torneoId AND
 te.anio = @anio AND
 te.jugadorGanador = @idJugador AND
 t.torneoTipo = '500'
group by t.torneoTipo
select @M250 = count(*)
from Torneos t, TorneoEdicion te
where t.torneoId = te.torneoId AND
 te.anio = @anio AND
 te.jugadorGanador = @idJugador AND
 t.torneoTipo = '250'
group by t.torneoTipo
end




/*OK------------------------------------------------------------------------------------*/
/*Funcion C*/

create function torneosPorJugadorPorAnio(@idJugador int, @anio int)
returns int
as
begin
declare @cantidad int
set @cantidad = 0
select @cantidad = count(distinct p.torneoId)
from Partidos p
where (p.jugadorGanador = @idJugador OR
p.jugadorPerdedor = @idJugador) AND
p.anio = @anio
return @cantidad
end


/*ANDA ----------------------------------------------------------------*/
/*PROC E: */

create procedure enfrentamientosJugadores
@jugador1 int, @jugador2 int, @totalEnfrentamientos int out, @Jugador1Gano int out,
@jugador2Gano int out
as
begin
set @totalEnfrentamientos = 0
set @Jugador1Gano = 0
set @Jugador2Gano = 0
select @totalEnfrentamientos = miTabla1.Jugador1Ganó + miTabla2.Jugador2Ganó,
@Jugador1Gano = miTabla1.Jugador1Ganó, @jugador2Gano = miTabla2.Jugador2Ganó
from (select p.jugadorGanador, p.jugadorPerdedor, count(*) as 'Jugador1Ganó'
 from Partidos p
 where p.jugadorGanador = @jugador1 AND p.jugadorPerdedor = @jugador2
 group by p.jugadorGanador, p.jugadorPerdedor)miTabla1 ,
 (select p.jugadorGanador, p.jugadorPerdedor, count(*) as 'Jugador2Ganó'
 from Partidos p
 where p.jugadorGanador = @jugador2 AND p.jugadorPerdedor = @jugador1
 group by p.jugadorGanador, p.jugadorPerdedor)miTabla2
end



/*ANDA OK--------------------------------------------------------------------------------------*/
/*func G*/

create function jugadoresPorPais(@jugadorId int)
returns int
as
begin
declare @cantidad int
set @cantidad = 0
declare @pais int
select @pais = j.paisNacimiento
from Jugadores j
where j.jugadorId = @jugadorId
select @cantidad = count(miTabla.paisNacimiento)
from (
/*Jugadores que jugaron algun torneo este año*/
select distinct j.jugadorId, j.paisNacimiento
from Jugadores j, Partidos p
where (j.jugadorId = p.jugadorGanador OR
j.jugadorId = p.jugadorPerdedor) AND
p.anio = year(getdate()))miTabla
where miTabla.paisNacimiento = @pais
group by miTabla.paisNacimiento
return @cantidad
end


