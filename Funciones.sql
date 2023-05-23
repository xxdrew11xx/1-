
-- FN para funciones escalares
-- IF para funciones de tabla

select * from tLibros
SELECT AVG(precio), editorial from tLibros group by editorial


--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--Funcion retornar libro segun isbn

IF OBJECT_ID('Funcion_1', 'FN') IS NOT NULL
	DROP FUNCTION Funcion_1

CREATE FUNCTION Funcion_1(@isbn char(13))
returns varchar(50)
as
BEGIN

	return (SELECT titulo FROM tLibros where isbn = @isbn)

END 

select dbo.Funcion_1('14-10-82338-0')

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--precio medio de los libros que pertenezcan a la editorial que tiene el libro

IF OBJECT_ID('F_medio', 'FN') IS NOT NULL
	DROP FUNCTION F_medio
	
CREATE FUNCTION F_medio(@isbn char(13))
RETURNS money
AS
begin
	
	RETURN (select AVG(precio) from tLibros where editorial=(select editorial from tLibros where isbn=@isbn))
	
end

select dbo.F_medio('14-10-82338-0')


--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--proyecte isbn titulo autor fecha de edicion con formato dia/3dmes/año nombre edirotial nombre prov de todos los libros que hayan sido editados en una fecha posterior a la que le diga

IF OBJECT_ID('F_FechaPost','IF') IS NOT NULL
	DROP FUNCTION F_FechaPost
	
CREATE FUNCTION	F_FechaPost (@fecha datetime)
returns table
as 
return (select tl.titulo, tl.autor, CONVERT(varchar, tl.fech_ed, 106) as 'Fecha', te.nombre as 'Editorial', tp.nombre as 'Provincia'	from tLibros tl, tEditoriales te, tProvincias tp  where te.codigo = tl.editorial and tp.codigo = te.cod_prov and tl.fech_ed > @fecha)	


select * from dbo.F_FechaPost('2008-10-01')


--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- numero de tuplas que cumplan la siguiente condicion, (comienzo, leyendo con un salto de, de todos aquellos libros que en el titulo tengan como mucho la letra, 'a' ) 

IF OBJECT_ID('F_cuentadetuplas','FN') IS NOT NULL
	DROP FUNCTION F_cuentadetuplas
	
create function F_cuentadetuplas(@inicio int, @salto int, @numlet int, @letra char(1))
returns int
as
BEGIN
	declare cursortupla cursor
	local keyset
	FOR SELECT titulo FROM tLibros
	DECLARE @titulo varchar(50), @i int, @contTitulos int, @contarLetras int
	set @contTitulos = 0

	OPEN cursortupla

	FETCH ABSOLUTE @inicio FROM cursortupla INTO @titulo

	while @@fetch_status = 0
	begin
	
		set @i = 0
		set @contarLetras = 0
	
		while @i <= len(@titulo)
		BEGIN 
		
			if SUBSTRING(@titulo, @i, 1) = @letra
			BEGIN 
			
				set @contarLetras = @contarLetras + 1
			
			END
		
			set @i = @i + 1
		
		END
	
		IF @contarLetras <= @numlet and @contarLetras > 0
		BEGIN 
		
			set @contTitulos = @contTitulos + 1
		
		END
	
		fetch relative @salto from cursortupla into @titulo
	
	end

	--print '[!] El número de libros que tienen solo ' + CONVERT(varchar,@numletra) + ' letras ' + CONVERT(varchar,@letra) + ' es: ' + CONVERT(varchar,@contTitulos)

	CLOSE cursortupla
	DEALLOCATE cursortupla


	return @contTitulos

end

print '[+] El numero de titulos que solo tienen ' + CONVERT(varchar, 5) + ' a es: ' + CONVERT(varchar,dbo.F_cuentadetuplas(5,3,5,'a'))



--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- todos los titulos que empiecen por la misma letra que el titulo del libro que se corresponda con el isbn que le mandas como parametro

IF OBJECT_ID('F_NombreISBN','IF') IS NOT NULL
	DROP FUNCTION F_NombreISBN
	
create function F_NombreISBN(@isbn char(13))
returns table
as
return (select tl.titulo as Titulo, editorial as Editorial, isbn as ISBN from tLibros tl where SUBSTRING(titulo, 1, 1) = (select SUBSTRING(tl2.titulo, 1 , 1) from tLibros tl2 where isbn=@isbn))


select * from F_NombreISBN('84-01-46272-X') 

select titulo, isbn from tLibros tl 

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- titulo y editorial de todos los libros que empiecen por la misma letra del isbn que pase como parametro y además su editorial tenga una media de los precios superior a la media de la editorial a la que pertenece el libro
	

IF OBJECT_ID('F_TituloEdit','IF') IS NOT NULL
	DROP FUNCTION F_TituloEdit
	
create function F_TituloEdit(@isbn char(13))
returns table
as
return (select tl.titulo as Titulo, editorial as Editorial from tLibros tl where SUBSTRING(titulo, 1, 1) = (select SUBSTRING(tl2.titulo, 1 , 1) from tLibros tl2 where isbn=@isbn) and (select AVG(precio) from tLibros tl2 group by editorial having tl.editorial = tl2.editorial) > (select dbo.F_medio(@isbn)))



return (select titulo, editorial from tLibros tl where titulo in (select titulo from F_NombreISBN(@isbn) ) and editorial in (select editorial from tLibros tl3 group by editorial having avg(precio)> (select dbo.F_medio(@isbn))))


	
select * from F_TituloEdit('84-01-46272-X')


--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- multisentencia ejemplo

if OBJECT_ID('F_Pmultisent', 'TF') is not null
	drop function F_Pmultisent
	
create function	F_Pmultisent()
returns @vtable table(editorial char(4), numlib int)
as
BEGIN
	
	insert into @vtable select editorial, count(*) from tLibros tl group by editorial
	return 
	
END

select * from dbo.F_Pmultisent()

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- lo mismo pero solo con aquellas editoriales que tengan más de un numero de libros que yo le envie por parametro

if OBJECT_ID('F_editorialNum', 'TF') is not null
	drop FUNCTION F_editorialNum
	
create function	F_editorialNum(@num int)
returns @vtable table(editorial char(4), numlib int)
as
BEGIN
	
	insert into @vtable select editorial, count(*) from tLibros tl group by editorial having count(*) > @num
	return 
	
END

select * from dbo.F_editorialNum(6) order by numlib

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- numero de linea, titulo, editorial

if OBJECT_ID('F_numLineasEdit', 'TF') is not null
	drop FUNCTION F_numLineasEdit
	
create function	F_numLineasEdit(@edit char(4))
returns @vtable table(NumeroLinea int identity(5,3), titulo varchar(50), editorial char(4))
as
BEGIN
	
	insert into @vtable (titulo,editorial) select titulo, editorial from tLibros tl where editorial = @edit
	return 
	
END

select * from dbo.F_numLineasEdit('PLAZ')

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--tabla que numero de lineas titulo nombre editorial y numvoc

if OBJECT_ID('F_NumVocNumLin', 'TF') is not null
	drop function F_NumVocNumLin
		
create function F_NumVocNumLin(@edit char(4))
returns @vtable table(Numlinea int identity(1,1), titulo varchar(50), nombreEdit varchar(35), numvocales int)
as
BEGIN
	
	insert into @vtable (titulo, nombreEdit, numvocales) select tl.titulo, te.nombre, LEN(titulo) - LEN(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE
	(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(titulo,'Ü',''),'Ú',''),'Ó',''),'Í',''),'É',''),'Á',''),'ú',''),'ó',''),'í',''),'é',''), 'á', ''), 'a', ''), 'e', ''), 'i', ''), 'o', ''), 'u', ''), 'A', '')
	, 'E', ''), 'I', ''), 'O', ''), 'U', '')) from tLibros tl, tEditoriales te where tl.editorial = @edit and tl.editorial = te.codigo 
	return
    
END

	
select * from dbo.F_NumVocNumLin('PLAZ')


--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--

if OBJECT_ID('F_DobleEdit', 'TF') is not null
	drop function F_DobleEdit
		
create function F_DobleEdit(@edit1 char(4), @edit2 char(4))
returns @vtable table(Numlinea int identity(1,1), titulo varchar(50), nombreEdit varchar(35))
as
BEGIN
	
	insert into @vtable (titulo, nombreEdit) select tl.titulo, te.nombre from tLibros tl, tEditoriales te where tl.editorial =te.codigo and (tl.editorial = @edit1 or tl.editorial = @edit2) order by tl.editorial 
	return
	
END

select * from F_DobleEdit('PLAZ', 'ALFA')

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- tabla tuplas numeradas, titulo, precio, y nombre edit || ejecutar funcion para sacar los dos primeros titulos que aparezcas

if OBJECT_ID('F_DobleFunc', 'TF') is not null
	drop function F_DobleFunc
	
CREATE FUNCTION F_DobleFunc(@edit char(4))
RETURNS @vtable table(NumTupla int identity(1,1), titulo varchar(50), precio money, nombreEditorial varchar(35))
as 
BEGIN
	
	insert into @vtable (titulo, precio, nombreEditorial) select titulo, precio, te.nombre from tLibros tl, tEditoriales te where tl.editorial = @edit and tl.editorial = te.codigo order by titulo
	return
	
END

select TOP 2 * from dbo.F_DobleFunc('ANAY')


--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- 

use BD_muebles

create table tFamilias(codigo char(5) primary key, nombre varchar(15))
create table tProvincias(codigo int primary key, nombre varchar(23))

create table tProovedores(codigo char(4) primary key, nombre varchar(30), cod_prov int references tProvincias(codigo))

create table tMuebles( codigo char(6), familia char(5) references tFamilias(codigo), denominacion varchar(20),
cod_prov char(4) references tProovedores(codigo), tipo char(1) NOT NULL DEFAULT 'O' CHECK(tipo like '[ABCDFO]'), stock_max decimal(9,2),stock_min decimal(9,2), stock_act decimal(9,2), precio money,
aviso char(1) DEFAULT 'N' CHECK(aviso like '[ABN]'), primary key(codigo, familia)) 

drop table tMuebles 


use BD_Biblioteca

drop database BDprueba

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- denominacion, nombre de proov nombre de provincia, nombre familia y lo que falta para stock maximo stando en stock minimo

if OBJECT_ID('vista1tMuebles', 'V') is not null
	drop view vista1tMuebles

create view vista1tMuebles
as 
select denominacion, familia, tp.nombre, tp2.nombre as 'Nombre de Proovedor', (stock_max - stock_act) as 'Para pedir' from tMuebles tm, tProvincias tp, tProovedores tp2  where (tm.cod_provedor  = tp2.codigo) and (stock_act <= stock_min) and (tp2.cod_provincia  = tp.codigo_provincia)


select * from vista1tMuebles

select * from tMuebles tm 

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- codigo denominacion precio

if OBJECT_ID('F_codeDenomPrec', 'TF') is not null
	drop FUNCTION F_codeDenomPrec
	
create function	F_codeDenomPrec()
returns @vtable table(codigo char(6), denominacion varchar(20), precio money)
as
BEGIN
	
	insert into @vtable select codigo, denominacion, precio from tMuebles where precio = (select max(precio) from tMuebles )
	return 
	
END

select * from F_codeDenomPrec()


select * from tMuebles tm 
update tmuebles set precio = 125.32 where codigo = 'GHR4'

--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
-- desencadenador tpedidos que va a contener el codigo del articulo el codigo de la familia el codigo del proovedor las unidades pedidas que seran la que falten para el stock maximo el valor del pedido y fecha del sistema(fech pedido)
modificando stock actual pase a estra en stock minimo

IF ('T_triigertMuebles', 'TR') is not NULL 
	drop TRIGGER T_triigertMuebles
	
create trigger T_triigertMuebles
on tMuebles
after update
	 as 
	 declare @stockAct int, @stockmin int, @stockMax int
	 select @stockAct = stock_act, @stockMin = stock_min, @stockMax = stock_max from tMuebles tm where codigo = (select codigo from update)
	 
	if not exists(select name from sysobjects where name='tPedidos' and type='U')
	 	create  table tPedidos(codigo char(6), codigo_Familia char(5), codigoprov char(4), unidades_Pedidas int, precio money)
	 	
	 if()



























