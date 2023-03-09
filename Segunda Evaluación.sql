select * into #ttemporal from tLibros //un hashtag para tabla local y dos para tabla global//

select * from #ttemporal
delete from #ttemporal where genero = 'ensayo'
drop table #ttemporal

select @@version
select @@LANGUAGE
select @@SERVERNAME
select * from tLibros
select @@rowcount

declare @nombre varchar(12), @edad int, @apellido varchar(12)
set @nombre = 'Juan'
set @edad = '24'
set @apellido = 'P�rez'
select @nombre, @edad
print @nombre+@apellido+str(@edad,3)

declare @titulo varchar(50), @autor varchar(30)
select @titulo = titulo, @autor = autor from tLibros where isbn = '14-10-82338-0'
print @titulo+'     '+@autor

declare @media decimal (6,2)
set @media = (select avg(precio) from tLibros)
print @media

declare @tabla table(titulo varchar(50), codedi char(4), precio money) insert into @tabla select titulo, editorial, precio from tLibros select * from tLibros

declare @numero int
set @numero=42
if @numero>10
begin
	print 'Valor alto'
	print 'M�s de diez'
end
else
	print 'Valor bajo'
print 'Fin del proceso'

declare @numero int
set @numero=0
while @numero < 10 
	begin
		set @numero=@numero+1	
		if @numero = 5 continue
		print 'N�mero = ' + str(@numero,3)
end
	print 'Fin del programa'


declare @numero int
set @numero=0
while @numero < 10 
	begin
		set @numero=@numero+1	
		if @numero = 5 
		begin 
				set @numero = 10
				continue
		end
		print @numero
end
	print 'Fin del programa'

declare @numero int
set @numero=0
while @numero < 10 
	begin
		set @numero=@numero+1	
		if @numero = 5 break		
		print @numero
end
	print 'Fin del programa'



declare @contador int
set @contador = 0
salto2:
print 'primero'
print 'segundo'
set @contador = @contador + 1
print @contador
if @contador = 2 goto salto1
print 'tercero'
print 'cuarto'
salto1:
print 'quinto'
print 'sexto'
print 's�ptimo'
goto salto2

select e.nombre, p.nombre from tEditoriales e, tProvincias p where cod_prov = p.codigo

select nombre, case cod_prov

						when 15 then 'A Coru�a'	
						when 27 then 'Lugo'
						when 32 then 'Ourense'
						when 36 then 'Pontevedra'
						else 'Resto de Espa�a' 
						end as Provincia
						from tEditoriales


select titulo, precio, case
					when precio > 15 then 'Caro'
					else 'Barato' 
					end  as 'Tipo de libro'
					from tLibros


create view vista1 (Nombre, Escritor, Valor)
as 
select titulo, autor, precio  from tLibros

select Nombre, Valor from vista1

select * from sysobjects where type = 'p'

drop view vista1 


declare Separaci�n_Proporcional cursor 
local keyset
for select titulo, precio from tlibros
declare @titulo varchar(50), @precio money, @tpuntos int

open Separaci�n_Proporcional 
fetch first from Separaci�n_Proporcional into @titulo,@precio
while @@fetch_status = 0
begin
    set @tpuntos = 55-len(@titulo)
    print @titulo+replicate('.',@tpuntos)+str(@precio,6,2)
    fetch next from Separaci�n_Proporcional into @titulo,@precio
end
close Separaci�n_Proporcional
deallocate Separaci�n_Proporcional


declare cursorEmilio cursor 
keyset local 
for select titulo, autor, precio from tlibros
declare @titulo char(50), @autor char(30), @precio money, @inicio int, @salto int, @max int,@cont int 
set @inicio = 1
set @salto = 3
set @max = 100
set @cont = 1
open cursorEmilio
fetch absolute @inicio from cursorEmilio into @titulo, @autor, @precio 
while @@FETCH_STATUS=0 and @cont<=@max
begin
    print str(@cont,4)+ ' '+@titulo+@autor+str(@precio,3)
    fetch relative @salto from cursorEmilio into @titulo,@autor,@precio
    set @cont = @cont + 1
    if @@FETCH_STATUS != 0 
    begin
    fetch first from cursorEmilio into @titulo,@autor,@precio
    set @cont = @cont + 1
    end
end 

close cursorEmilio
deallocate cursorEmilio


declare titulosMenosVocales cursor
keyset
for select titulo from tlibros
declare @titulo char(50)
declare @numVocales int, @numVocalesM int, @posLet int
declare @ttitulos table (titulo varchar (15))
open titulosMenosVocales 
set @numVocales = 0
set @numVocalesM = 999
fetch first from titulosMenosVocales into @titulo
while @@FETCH_STATUS = 0
begin
    set @posLet = 1
    while @posLet <= len(@titulo)
    begin 
        if substring (@titulo,@posLet,1) like ('[aeiou������]')
        begin
            set @numVocales = @numVocales + 1
        end
        set @posLet= @posLet + 1
    end 
    if @numVocales <= @numVocalesM
    begin 
        if @numVocales < @numVocalesM
        begin 
            delete from @ttitulos
            set @numVocalesM= @numVocales
        end
        insert into @ttitulos values (@titulo)
    end 
    set @numVocales=0
    fetch next from titulosMenosVocales into @titulo 
end 
select titulo, @numVocalesM 'N�mero vocales' from @ttitulos
close titulosMenosVocales
deallocate titulosMenosVocales


declare cursorRuptura1 cursor
keyset local
for select titulo, precio, editorial from tlibros order by editorial
declare @titulo char(50), @precio money, @editorial char(4), @editAnt char(4), @totalPrecio money
set @totalPrecio = 0
open cursorRuptura1 
fetch first from cursorRuptura1 into @titulo, @precio, @editorial
set @editAnt = @editorial
while @@FETCH_STATUS = 0
begin 
		if @editorial != @editAnt
		begin 
				print 'Total editorial '+@editAnt+' --------> '+str(@totalPrecio, 7, 2)
				set @totalPrecio = 0
				set @editAnt = @editorial
		end
		print @titulo+ ' '+str(@precio,6,2)
		set @totalPrecio = @totalPrecio + @precio

		fetch from cursorRuptura1 into @titulo, @precio, @editorial
		
end				

print 'Total editorial '+@editAnt+' --------> '+str(@totalPrecio, 7, 2)

close cursorRuptura1
deallocate cursorRuptura1


declare cursorRuptura1 cursor
keyset local 
for select  titulo, precio, editorial from tlibros  order by editorial
declare @titulo char (50), @precio money, @editorial char (4), @editAnt char (4)
declare @totalPrecio money
set @totalPrecio = 0
open cursorRuptura1 
fetch first from cursorRuptura1 into @titulo, @precio, @editorial
set @editAnt= @editorial 
while @@FETCH_STATUS = 0
begin 
    if @editorial != @editAnt
    begin
        print 'Total editorial '+@editAnt+' ---------> '+str(@totalPrecio,7,2)
        set @totalPrecio = 0
        set @editAnt = @editorial
    end
    print @titulo+'  '+str(@precio,6,2)
    set @totalPrecio = @totalPrecio + @precio
    fetch next from cursorRuptura1 into @titulo,@precio,@editorial
end
print 'Total editorial '+@editAnt+' ---------> '+str(@totalPrecio,7,2)
close cursorRuptura1
deallocate cursorRuptura1



declare cursorRuptura2 cursor
keyset local 
for select  titulo, precio, editorial from tlibros  order by editorial
declare @titulo char (50), @precio money, @editorial char (4), @editAnt char (4), @nombreEdit char (50)
declare @totalPrecio money
set @totalPrecio = 0
open cursorRuptura2
fetch first from cursorRuptura2 into @titulo, @precio, @editorial
set @editAnt= @editorial 
while @@FETCH_STATUS = 0
begin 
    if @editorial != @editAnt
    begin
        print ' '
        select @nombreEdit = nombre from teditoriales where codigo = @editAnt
        print ' '
        print 'Total editorial '+@nombreEdit+' ---------> '+str(@totalPrecio,7,2)
        set @totalPrecio = 0
        set @editAnt = @editorial
    end
    print @titulo+'  '+str(@precio,6,2)
    set @totalPrecio = @totalPrecio + @precio
    fetch next from cursorRuptura2 into @titulo,@precio,@editorial
end
print 'Total editorial '+@editAnt+' ---------> '+str(@totalPrecio,7,2)
close cursorRuptura2
deallocate cursorRuptura2


declare cursorRupturaEncuadernacion cursor
keyset local
for select titulo, precio, encuadernacion from tlibros order by encuadernacion
declare @titulo char(50), @precio money, @encuadernacion char(10), @encAnt char(10)
declare @totalPrecio money, @totalGeneral money, @numEnc int, @mediaEnc money
set @totalPrecio = 0
set @numEnc = 0
set @mediaEnc = 0
open cursorRupturaEncuadernacion
fetch first from cursorRupturaEncuadernacion into @titulo, @precio, @encuadernacion
set @encAnt = @encuadernacion
while @@fetch_status = 0
begin
    if @encuadernacion != @encAnt
    begin
    print' '
    print 'Total encuadernacion '+@encAnt+'-----> '+str(@totalPrecio,7,2)
    set @mediaEnc = @mediaEnc + @totalPrecio
    set @totalPrecio = 0
    set @numEnc = @numEnc+1
    set @encAnt = @encuadernacion
    end
    print @titulo+' '+str(@precio,6,2)
    set @totalPrecio = @totalPrecio + @precio
    fetch next from cursorRupturaEncuadernacion into @titulo, @precio, @encuadernacion
end
print' '
set @mediaEnc = @mediaEnc +@totalPrecio
set @numEnc = @numEnc + 1
print 'Total encuadernacion '+@encAnt+'---> '+str(@precio,7,2)
print ' '
print 'Total General ----> '+str(@mediaEnc,8,2)
print 'N�mero de encuadernaciones----> '+str(@numEnc)

print 'Media por encuadernaci�n ---> '+str(@mediaEnc/@numEnc,8,2)
close cursorRupturaEncuadernacion
deallocate cursorRupturaEncuadernacion





declare totalesEncuadernacion cursor 
keyset local 
for select titulo, encuadernacion from tlibros order by encuadernacion
declare @titulo char(50), @encuadernacion char(15)
declare @encAnt char (15), @numLib int 
set @numLib = 0
open totalesEncuadernacion 
fetch first from totalesEncuadernacion into @titulo,@encuadernacion 
set @encAnt = @encuadernacion 
while @@FETCH_STATUS = 0
begin 
    if @encuadernacion <> @encAnt
    begin 
        print ' '
        print 'Totales...................: '+@encAnt+' '+str(@numLib)+ ' Libros'
        print ' '
        set @encAnt = @encuadernacion
        set @numLib = 0
    end
    print @titulo+' '+@encuadernacion
    set @numLib = @numLib + 1 
    fetch next from totalesEncuadernacion into @titulo,@encuadernacion
end
print ' '
print 'Totales.............................: '+@encAnt+' '+str(@numLib)+ ' Libros'
print ' '
close totalesEncuadernacion 
deallocate totalesEncuadernacion






declare totalesAutorEditorial cursor
keyset local
for select titulo, autor, editorial, precio from tLibros order by editorial, autor
declare @titulo char(50), @autor char(30), @editorial char(4), @precio money
declare @AutorAnt char(30), @ediAnt char(4), @totAut money, @totEdi money
set @totAut = 0
set @totEdi = 0

open totalesAutorEditorial
	fetch first from totalesAutorEditorial into @titulo, @autor, @editorial, @precio
	set @ediAnt = @editorial
	set @autorAnt = @autor
	while @@FETCH_STATUS = 0

	begin	
			if @editorial != @ediAnt or @autor != @AutorAnt
			begin
				
				print 'Total '+@autorAnt+' ........'+convert(varchar,@totAut)
				set @totEdi = @totEdi + @totAut
				set @totAut = 0
				set @AutorAnt = @autor
				if @editorial != @ediAnt
				
				begin
					print 'Totales ..............: '+@ediAnt+' '+convert(varchar,@totEdi)+'�'
					set @ediAnt = @editorial
					set @totEdi = 0
				end
	end
	print @editorial+'          '+@autor+' '+@titulo+convert(varchar,@precio)
	set @totEdi = @totEdi + @totAut
	print 'Totales ..............: '+@ediAnt+' '+convert(varchar,@totEdi)+'�'
	close totalesAutorEditorial 
	deallocate totalesAutorEditorial 





declare cursorRupturaDoble cursor
keyset local
for select isbn, titulo, autor, precio, genero, editorial from tlibros order by editorial,genero 
declare @isbn char(13), @titulo char(50), @autor char(30), @precio money
declare @genero varchar(15), @editorial varchar(4)
declare @editAnt varchar(4), @generoAnt varchar(15)
declare @contGen int, @contEdit int

open cursorRupturaDoble
fetch first from cursorRupturaDoble into @isbn, @titulo, @autor, @precio,@genero, @editorial 
set @editAnt = @editorial
set @generoAnt = @genero
set @contGen = 0
set @contEdit = 0
while @@FETCH_STATUS = 0
begin 
    if @editAnt != @editorial or @generoAnt != @genero
    begin
        print 'Total libros del genero '+@generoAnt+' ====> '+ convert(varchar,@contGen)
        print ' '
        set @contGen = 0
        set @generoAnt = @genero
        if @editAnt != @editorial
        begin
            print 'Total libros de la editorial ' + @editAnt +' ========> '+ convert(varchar,@contEdit)
            print ' '
            print '__'
            print 'NUEVA EDITORIAL'
            set @editAnt = @editorial
            set @contEdit = 0
        end
    end
    print @isbn +' '+ @titulo + @autor + str(@precio,6,2)
    set @contGen = @contGen +1
    set @contEdit = @contEdit+1
    fetch next from cursorRupturaDoble into @isbn, @titulo, @autor, @precio,@genero, @editorial
end
print 'Total libros del genero '+@generoAnt+' ====> '+ convert(varchar,@contGen)
print ' '
print 'Total libros de la editorial ' + @editAnt +' ========> '+ convert(varchar,@contEdit)
close cursorRupturaDoble
deallocate cursorRupturaDoble



	select * from tEditoriales
    select * from tProvincias
	select * from tLibros




if exists (select name from sysobjects where name = 'P_primero' and type = 'P') drop procedure P_primero
go
create procedure P_primero
as
select * from tLibros

execute P_primero



drop procedure P_procedimiento1
drop procedure P_procedimiento2


create procedure P_procedimiento1 
as
print 'Estamos en el procedimiento 1' 
exec P_procedimiento2
print 'Volvemos a estar en el procedimiento 1'

create procedure P_procedimiento2 
as
print 'Estamos en el procedimiento 2'


exec P_procedimiento1
exec P_procedimiento2



create procedure P_ListadoEditorial @editorial char(4)
as
select * from tLibros where editorial = @editorial

exec P_ListadoEditorial 'ALFA'


titulo, autor, editorial, precio de todos los libros de una editorial (parametro) con precio superior al que le indique(parametro)



if exists (select name from sysobjects where name = 'P_primero' and type = 'P') drop procedure P_EditPrecio
go
create procedure P_EditPrecio @editorial char(4), @precio money
as
select * from tLibros where editorial = @editorial and precio > @precio

exec P_EditPrecio 'D', 12

select * from tLibros 



if exists (select name from sysobjects where name = 'P_AutorTitulo' and type = 'P') 
drop procedure P_EditPrecio
go
create procedure P_AutorTitulo
@titulo varchar(50), @autor varchar(30) output 
as
select @autor = autor from tLibros where titulo = @titulo

declare @aut varchar(30)
exec P_AutorTitulo 'Rebeca', @aut output
select @aut 'Autor'


if exists (select name from sysobjects where name = 'P_Listado_Entre_Precio' and type = 'P') 
drop procedure P_Listado_Entre_Precio
go
create procedure P_Listado_Entre_Precio
@precio1 money, @precio2 money 
as
if @precio1 > @precio2
	select titulo, autor, precio from tLibros where precio between @precio1 and @precio2 order by precio
else 
	select titulo, autor, precio from tLibros where precio between @precio1 and @precio2 order by precio

execute P_Listado_Entre_Precio 11, 15


if exists (select name from sysobjects where name = 'P_Libro_Mes' and type = 'P') 
drop procedure P_Libro_Mes
go
create procedure P_Libro_Mes
@mes char(10)
as
select titulo, datename(dd, fech_ed)+' de '+ substring(datename(mm, fech_ed), 1, 3)+' '+ datename(yy, fech_ed) Fecha, 
datename(dd, fech_ed)+' de '+datename(mm, fech_ed)+' de '+ datename(yy, fech_ed) Fecha from tlibros where datename(mm, fech_ed) = @mes	


execute P_Libro_Mes 'Marzo'


isbn, titulo, autor, nombre de la editorial que introduzcamos(codigo)


if exists (select name from sysobjects where name = 'P_Titulo_Autor_Editorial' and type = 'P') 
drop procedure P_Titulo_Autor_Editorial
go
create procedure P_Titulo_Autor_Editorial
@editorial char(4)
as
select isbn,titulo, autor, tl.editorial from tLibros tl, tEditoriales te where tl.editorial = @editorial and te.codigo = @editorial

exec P_Titulo_Autor_Editorial 'PLAZ'



if exists (select name from sysobjects where name = 'P_Libro_ISBN' and type = 'P') 
drop procedure P_Libro_ISBN
go
create procedure P_Libro_ISBN
@isbn char(13)
as
declare @editorial char(4)
select @editorial = editorial from tLibros where isbn = @isbn
execute P_Titulo_Autor_Editorial @editorial
execute P_Libro_ISBN '84-8450-037-3'



libros comprendidos entre una fecha

if exists (select name from sysobjects where name = 'P_Fechas' and type = 'P') 
drop procedure P_Fechas
go
create procedure P_Fechas
@fecha1 date, @fecha2 date
as
begin
if @fecha1 < @fecha2
	select titulo, autor, fech_ed from tLibros where fech_ed between @fecha1 and @fecha2 order by fech_ed
else
select titulo, autor, fech_ed from tLibros where fech_ed between @fecha2 and @fecha1 order by fech_ed
end

execute P_Fechas '1/8/00', '12/10/80'



enviamos cod editorial y queremos averiguar la fech ed mas cercana de antes y despues entre esas fechas

if exists (select name from sysobjects where name = 'P_Fechas_Editorial' and type = 'P') 
drop procedure P_Fechas_Editorial
go
create procedure P_Fechas_Editorial
@editorial char(4)
as
declare @fecha1 date, @fecha2 date
select @fecha1=min(fech_ed), @fecha2=max(fech_ed) from tLibros tl, tEditoriales te where tl.editorial = @editorial and te.codigo = @editorial
execute P_Fechas @fecha1, @fecha2

execute P_Fechas_Editorial 'PLAZ'



enviamos editorial y queremos su provincia


if exists (select name from sysobjects where name = 'P_Editorial_Provincia' and type = 'P') 
drop procedure P_Editorial_Provincia
go
create procedure P_Editorial_Provincia
@editorial char(4), @prov varchar(23) output
as
select @prov = tp.nombre from tProvincias tp, teditoriales te where @editorial = te.codigo and te.cod_prov = tp.codigo


declare @prov varchar(25)
execute P_Editorial_Provincia 'PLAZ', @prov output
print @prov 



que tenga m�s vocales que las que le indicamos



if exists (select name from sysobjects
    where name='P_DarNumeroVocales' and type='P')
    drop procedure P_DarNumeroVocales
go
create procedure P_DarNumeroVocales
    @nv int
as
declare cursorDarTitulo cursor
keyset local
for select titulo from tlibros
declare @titulo varchar(50)
declare @contVocales int, @posLet int
set @contVocales=0

    open cursorDarTitulo
    fetch first from cursorDarTitulo into @titulo
    while @@fetch_status = 0
    begin
        set @posLet=1
        while @posLet <=len(@titulo)
        begin
            if (substring(@titulo,@posLet,1) like '[aeiou������')
            begin
                set @contVocales = @contVocales+1
            end
            set @posLet = @posLet + 1
        end
        if @contVocales >= @nv
        begin
            print @titulo + ' -> ' + str(@contVocales)
        end
        set @contVocales=0
        fetch next from cursorDarTitulo into @titulo
    end
    close cursorDarTitulo
    deallocate cursorDarTitulo

exec P_DarNumeroVocales 19







if exists (select name from sysobjects
    where name='P_DarNumeroVocalesYMasLargo' and type='P')
    drop procedure P_DarNumeroVocalesYMasLargo
go
create procedure P_DarNumeroVocalesYMasLargo
    @nv int
as
declare cursorDarTituloYMasLargo cursor
keyset local
for select titulo from tlibros
declare @titulo varchar(50), @tituloMasLargo varchar(50)
declare @contVocales int, @posLet int, @contLetras int
set @contVocales = 0
set @contLetras = 0

    open cursorDarTituloYMasLargo
    fetch first from cursorDarTituloYMasLargo into @titulo
    while @@fetch_status = 0
    begin
        set @posLet=1
        while @posLet <=len(@titulo)
       
	   begin
			set @contLetras = @contLetras + 1
			if (substring(@titulo,@posLet,1) like ' ')
            begin
                set @contVocales = @contVocales-1
            end

            if (substring(@titulo,@posLet,1) like '[aeiou������')
            begin
                set @contVocales = @contVocales+1
            end
            set @posLet = @posLet + 1
        end

        if @contVocales >= @nv 
        begin
            print @titulo + ' -> ' + str(@contVocales)
        end

		if @contLetras >= len(@titulo)
		 begin
            print @titulo + ' -> ' + str(@contLetras)
        end

        set @contVocales=0
        fetch next from cursorDarTituloYMasLargo into @titulo
    end
    close cursorDarTituloYMasLargo
    deallocate cursorDarTituloYMasLargo


	exec P_DarNumeroVocalesYMasLargo 19

	
	
	
	if exists (select name from sysobjects
    where name='P_DarNumeroVocales' and type='P')
    drop procedure P_DarNumeroVocales
go
create procedure P_DarNumeroVocales
    @nv int
as
declare cursorDarTitulo cursor
keyset local
for select titulo from tlibros
declare @titulo varchar(50), @maxTit varchar (50)
declare @contVocales int, @posLet int

    open cursorDarTitulo
    fetch first from cursorDarTitulo into @titulo
    set @maxTit = @titulo
    while @@fetch_status = 0
    begin
        set @posLet=1
		set @contVocales=0
        while @posLet <=len(@titulo)
        begin
            if (substring(@titulo,@posLet,1) like '[aeiou������]')
            begin
                set @contVocales = @contVocales+1
            end
            set @posLet = @posLet + 1
        end
        if @contVocales >= @nv
        begin
            print @titulo + ' -> ' + str(@contVocales)
        end
        set @contVocales=0
        fetch next from cursorDarTitulo into @titulo
        if (len(@maxTit) < len(@titulo))
        begin
            set @maxTit = @titulo
        end

    end
    print 'El t�tulo m�s largo -> ' + @maxTit
    close cursorDarTitulo
    deallocate cursorDarTitulo

exec P_DarNumeroVocales 18




select len(replace(titulo,'[^AEIOUaeiou������]', ' ')) as vocales from tlibros
select * from tEditoriales te, tProvincias tp  where te.cod_prov = tp.codigo



select * from tLibros



if EXISTS(select name from sysobjects where name='T_Primero' and type='TR')
	drop trigger T_Primero

CREATE trigger T_Primero
on tLibros
after UPDATE
	as 
	print '[!] Ejecutando T_Primero'
	
	update tLibros set precio = 21.99 where isbn='0-261-10320-2'

if EXISTS(select name from sysobjects where name='T_Segundo' and type='TR')
	drop trigger seg

CREATE trigger T_Segundo
on tLibros
after UPDATE
	as 
	print '[!] Ejecutando T_Segundo'
	
	update tLibros set precio = 21.99 where isbn='0-261-10320-2'




sp_settriggerorder 'T_Primero', 'Last', 'Update'
sys.sp_settriggerorder('seg', 'FIRST',  'Update')

alter table tLibros disable TRIGGER T_Segundo



if EXISTS(select name from sysobjects where name='T_AntesDespues' and type='TR')
	drop trigger seg

CREATE trigger T_AntesDespues
on tLibros
after UPDATE
	as 
	
	DECLARE @tanterior varchar(50), @titulonuevo varchar(50)

	select @tanterior = titulo from deleted
	select @titulonuevo = titulo from inserted

print 'Titulo anterior = ' + @tanterior
print 'Titulo nuevo = ' + @titulonuevo



create table tsocios (
						nif char(9) PRIMARY KEY,
						nombre varchar(15),
						ape1 varchar(15),
						ape2 varchar(15),
						edad int,
						direccion varchar(25),
						provincia int REFERENCES tProvincias
					)
				
create table tprestamos (
							nif char(9) not null REFERENCES tsocios,
							isbn char(13) not null REFERENCES tLibros,
							fech_pres DATETIme not null default getdate(),
							fech_dev datetime not null default dateadd(dd,15,getdate()),
							PRIMARY key(nif, isbn)
							)
				
insert into tsocios VALUES('12345678J', 'Águeda', 'Pérez', 'Quinzás', 28, 'C/Pérez Ardá, Plaza-8-3', 36)

select * from tSocios

alter TABLE tSocios ALTER COLUMN direccion varchar(40)

select * from tLibros

insert into tPrestamos (nif, isbn) VALUES ('11111111A', '84-01-46178-1')



cada vez que se preste un libro, crear tlibpres el isbn, titulo, autor, fecha de devolucion

if EXISTS(select name from sysobjects where name='T_Prestamos' and type='TR')
	drop trigger T_Prestamos

CREATE trigger T_Prestamos
on tLibros
after insert 
	as 
	
	if not EXISTS(select name from sysobjects where name = 'tLibPres' and type = 'U')
	BEGIN
		
		select inserted.isbn, titulo, autor, fech_dev into tLibPres from tLibros, inserted where tLibros.isbn = inserted.isbn
		
	END
	ELSE
	BEGIN
		
		insert into tLibPres (isbn, titulo, autor, fech_dev) values (isbn)
		
	END

insert into tPrestamos (nif, isbn) VALUES ('33333333C', '84-01-42282-5')


if EXISTS(select name from sysobjects where name='T_PrestamosDel' and type='TR')
	drop trigger T_PrestamosDel

create TRIGGER T_PrestamosDel
on tLibPres
after DELETE
as
	
	if @@rowcount = 0 
	
		print 'no existe el prestamo'
	
	ELSE
	BEGIN
		
		delete from tLibPres  where isbn in (select isbn from deleted)
		
		print 'libro/s borrados correctamente'
		
		IF ((select count(*) from tLibPres)=0)
		BEGIN
			
			drop table tLibPres
			print 'Tabla de libros prestados se ha eliminado'
			
		END
		
	END
	
		DECLARE @isbn char(13), @titulo varchar(50)
		select @isbn = isbn from deleted
		select @titulo = titulo from tLibros where isbn= @isbn

	delete from tPrestamos where isbn = @isbn

print 'El libro ' + @titulo + ' ha sido devuelto'


				

