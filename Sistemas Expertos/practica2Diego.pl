%Arbol genealogico.

%Se define la base de conocimiento con las relaciones basicas.

$Sexo de las personas para saber diferencias discriminativas de sexo, como HERMANO - HERMANA, hay que saber el sexo para decirlo
hombre(jorge).
hombre(diego).
hombre(joaquin).
hombre(joaquinjr).
hombre(cruzjr).
hombre(eric).
hombre(miguel).
hombre(ricardo).
hombre(cruz).
hombre(emanuel).
hombre(roberto).
hombre(carlos).

mujer(alma).
mujer(lolita).
mujer(isabel).
mujer(patricia).
mujer(ana).
mujer(karina).
mujer(rosaura).
mujer(daniela).
mujer(bibiana).
mujer(melisa).
mujer(valeria).
mujer(kenia).
mujer(miriam).
mujer(nicole).


%Relacion basica, quien es el progenitor de quien

progenitor(alma,diego).
progenitor(alma,valeria).
progenitor(jorge,diego).
progenitor(jorge,valeria).
progenitor(joaquin,joaquinjr).
progenitor(isabel,joaquinjr).
progenitor(cruzjr,eric).
progenitor(cruzjr,melisa).
progenitor(cruzjr,kenia).
progenitor(rosaura,eric).
progenitor(rosaura,melisa).
progenitor(rosaura,kenia).
progenitor(miguel,karina).
progenitor(miguel,daniela).
progenitor(miguel,bibiana).
progenitor(patricia,karina).
progenitor(patricia,daniela).
progenitor(patricia,bibiana).
progenitor(lolita,alma).
progenitor(lolita,isabel).
progenitor(lolita,ana).
progenitor(lolita,patricia).
progenitor(lolita,cruzjr).
progenitor(lolita,roberto).
progenitor(cruz,alma).
progenitor(cruz,isabel).
progenitor(cruz,ana).
progenitor(cruz,patricia).
progenitor(cruz,cruzjr).
progenitor(cruz,roberto).
progenitor(ana,ricardo).
progenitor(ana,emanuel).
progenitor(roberto,carlos).
progenitor(ricardo,nicole).
progenitor(miriam,nicole).

%Otra relacion basica, las parejas

pareja(lolita,cruz).
pareja(cruz,lolita).
pareja(jorge,alma).
pareja(alma,jorge).
pareja(isabel,joaquin).
pareja(joaquin,isabel).
pareja(cruzjr,rosaura).
pareja(rosaura,cruz).
pareja(miguel,patricia).
pareja(patricia,miguel).
pareja(miriam,ricardo).
pareja(ricardo,miriam).

%A partir de las relaciones basicas se determinan las inferencias, utlzando las siguientes reglas 
%de inferencia para poder obtener relaciones más especificas.
%Analizar cuales son las relaciones basicas, esas son las que se aregan a la base de conocimiento como arriba

padre(X,Y):-hombre(X),progenitor(X,Y). %Por ejemplo, aqui, en la consola se quiere saber el padre de alguien, pero eso no está en la base de conocimiento
%Así que se usa esta regla loica para saberlo;
madre(X,Y):-mujer(X),progenitor(X,Y).
hijo(X,Y):-hombre(X),progenitor(Y,X).
hija(X,Y):-mujer(X),progenitor(Y,X).
hermano(X,Y):-hombre(X),hermanos(X,Y).
hermana(X,Y):-mujer(X),hermanos(X,Y).
esposo(X,Y):-pareja(X,Y),hombre(X).
esposa(X,Y):-pareja(X,Y),mujer(X).
suegro(X,Y):-padre(X,Z),pareja(Y,Z).
suegra(X,Y):-madre(X,Z),esposos(Y,Z).
yerno(X,Y):-suegro(Y,X);suegra(Y,X),hombre(X).
nuera(X,Y):-suegro(Y,X);suegra(Y,X),mujer(X).
cuñado(X,Y):-cuñados(X,Y),hombre(X).
cuñada(X,Y):-cuñados(X,Y),mujer(X).
abuelo(X,Y):-progenitor(Z,Y),padre(X,Z).
abuela(X,Y):-progenitor(Z,Y),madre(X,Z).
nieto(X,Y):-progenitor(Y,Z),progenitor(Z,X),hombre(X).
nieta(X,Y):-progenitor(Y,Z),progenitor(Z,X),mujer(X).
tio(X,Y):-progenitor(Z,Y),(hermano(X,Z);cuñado(X,Z)).
tia(X,Y):-progenitor(Z,Y),(hermana(X,Z);cuñada(X,Z)).
primo(X,Y):-progenitor(Z,X),progenitor(W,Y),hermanos(Z,W),hombre(X).
prima(X,Y):-progenitor(Z,X),progenitor(W,Y),hermanos(Z,W),mujer(X).