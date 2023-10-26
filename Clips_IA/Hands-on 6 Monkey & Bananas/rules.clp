(defrule acercar-mono-a-silla
    ?hecho-actual <- (nextTo mono muro)
    =>
    (retract ?hecho-actual)
    (assert (nextTo mono silla) )
    (printout t "El mono se acerca a la silla" crlf)
)

(defrule subir-silla,
    ?hecho-actual  <- (isOn silla suelo)
    ?hecho-actual2 <- (nextTo mono silla)
    ?hechoactual3 <- (nextTo silla mesa)
    =>
    ( retract ?hecho-actual ?hecho-actual2 ?hechoactual3 )
    ( assert (isOn silla mesa) (nextTo mono mesa) )
    (printout t "El mono pone la silla sobre la mesa" crlf)
)


(defrule subir-mono-a-mesa
    ?hechoactual <- (isOn silla mesa)
    ?hechoactual1 <- (nextTo mono mesa)
    ?hechoactual2 <- (isOn mono suelo)
    =>
    (retract ?hechoactual1 ?hechoactual2 )
    (assert (isOn mono mesa))
    (printout t "El mono sube a la mesa" crlf)
)
 
(defrule subir-mono-a-silla
    ?hechoactual <- (isOn silla mesa)
    ?hechoactual1 <- (isOn mono mesa)
    =>
    (retract ?hechoactual1)
    (assert (isOn mono silla))
    (printout t "El mono sube a la silla" crlf)
)

(defrule mono-toma-la-banana
    ?hechoactual <-  (isOn mono silla)
    ?hechoactual1 <- (isOn banana techo)
    =>
    (retract ?hechoactual1)
    (assert (el-mono-tiene-la-banana))
    (printout t "El mono tiene la banana" crlf)
)

(defrule mono-come-la-banana
    ?hechoactual <-  (isOn mono silla)
    ?hechoactual1 <- (el-mono-tiene-la-banana)
    =>
    (retract ?hechoactual1)
    (assert (el-mono-come-la-banana))
    (printout t "El mono come la banana" crlf)
)
(defrule cambiar-estado-del-mono
    ?hechoactual <-  (el-mono-esta hambriento)
    ?hechoactual1 <-  (el-mono-come-la-banana) 
    =>
    (retract ?hechoactual ?hechoactual1)
    (assert (el-mono-esta feliz))
    (printout t "El mono está feliz" crlf)
)