;acomodado desde el inicio
;lo que ya esté acomodado, siempre y cuando la mesa esté incluida
; lo que esta acomodado y en un punto esta la mesa (bloques acomodados anidados)

(defrule acomodado-en-inicio-con-mesa 

    (on-final ?bloque1 mesa)
    (on ?bloque1 mesa)

    =>

    (assert (acomodado ?bloque1))

)

(defrule acomodado-en-inicio 

    (on-final ?bloque1 ?bloque2)
    (on ?bloque1 ?bloque2)
    (acomodado ?bloque2)

    =>

    (assert (acomodado ?bloque1))

)

;preparado:
;lo que este en la mesa y no tenga nada encima
;no tiene nada encima y su inferior esta listo para recibirlo (esta acomodado)


(defrule bloques-preparados-sueltos ;lo que este en la mesa y no tenga nada encima
    (on ?bloque1 mesa)
    (not (on ?bloque2 ?bloque1))

    =>

    (assert (preparado ?bloque1))
)

(defrule preparar-bloque-no-suelto ;no tiene nada encima y su inferior esta listo para recibirlo (esta acomodado),No esta suelto en la mesa

    ?h1 <- (on ?bloque1 ?bloque2)
    (not (on ?bloque3 ?bloque1))
    (on-final ?bloque1 ?bloque-inferior)
    (acomodado ?bloque-inferior)
    (not (on ?bloque1 mesa))
    (not (acomodado ?bloque1))

    =>

    (assert (preparado ?bloque1))

)    


(defrule pasar-a-la-mesa ;pasar los bloques no acomodados a la mesa que no tengan nada encima y no preparados
    ?h1 <- (on ?bloque1 ?bloque2)
    (not (acomodado ?bloque1))
    (not(preparado ?bloque1))
    (not (on ?bloque3 ?bloque1))
    (not (on ?bloque1 mesa))

    =>
    (retract ?h1)
    (assert (on ?bloque1 mesa))
    
    (printout t "Poner " ?bloque1 " sobre la mesa" crlf)

)

;acomodar los bloques que esten preparados
;los que van en la mesa y no estaban acomodados desde el inicio
;los que van sobre otros bloques

(defrule acomodar-en-mesa

    (on-final ?bloque1 mesa)
    (on ?bloque1 mesa)
    (not (acomodado ?bloque1))
    ?h1 <- (preparado ?bloque1)

    =>

    (retract ?h1)
    (assert (acomodado ?bloque1))

)

(defrule acomodar-sobre-otro-bloque

    (on-final ?bloque1 ?bloque-inferior)
    (acomodado ?bloque-inferior)
    (preparado ?bloque1)
    ?h1 <- (preparado ?bloque1)
    ?h2 <- (on ?bloque1 ?bloquex)
    (not (acomodado ?bloque1))

    =>

    (retract ?h1 ?h2)
    (assert (on ?bloque1 ?bloque-inferior))
    (assert (acomodado ?bloque1))

    (printout t "Colocar " ?bloque1 " sobre " ?bloque-inferior crlf)

)


