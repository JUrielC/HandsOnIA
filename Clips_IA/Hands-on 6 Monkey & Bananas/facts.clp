(deffacts estado-inicial-isOn
    (isOn mono suelo)
    (isOn mesa suelo)
    (isOn silla suelo)
    (isOn banana techo)
)

(deffacts estado-inicial-nextTo
    (nextTo mono muro)
    (nextTo silla mesa)    
)

(deffacts estado-inicial-mono
    (el-mono-esta hambriento)
)