(deftemplate medico
	(slot especialidad))

(deftemplate tratamiento
	(slot tipo))

(deftemplate enfermedad
	(slot nombre))

(deftemplate sintoma
	(slot sintom))

(deffacts medicos
	(medico (especialidad otorrino))
	(medico (especialidad endocrinologo))
	(medico (especialidad nutricionista))
	(medico (especialidad medico_general))
)

(deffacts tratamientos
	(tratamiento (tipo jarabe))
	(tratamiento (tipo contrex))
	(tratamiento (tipo vitamina))
	(tratamiento (tipo vacuna))
	(tratamiento (tipo pastilla))
)

(deffacts enfermedades
	(enfermedad (nombre gripe))
	(enfermedad (nombre rubeola))
	(enfermedad (nombre malaria))
	(enfermedad (nombre hepatitis))
	(enfermedad (nombre tuberculosis))
	(enfermedad (nombre anemia))
)

(deffacts sintomas
	(sintoma (sintom diarrea))
	(sintoma (sintom tos))
	(sintoma (sintom cansancio))
	(sintoma (sintom fiebre))
	(sintoma (sintom dolor_de_cabeza))
	(sintoma (sintom nauseas))
	(sintoma (sintom ictericia))
	(sintoma (sintom apatia))
	(sintoma (sintom escalofrios))
	(sintoma (sintom jaqueca))
	(sintoma (sintom secrecion))
)





(defrule sintoma_hepatitis
	(diarrea) (nauseas) (ictericia)
	=>
	(assert (enfermedad (nombre hepatitis)))
	(printout t "El paciente tiene hepatitis" crlf)
)

(defrule sintoma_gripe
	(tos) (cansancio) (fiebre) (dolor_de_cabeza)
	=>
	(assert (enfermedad (nombre gripe)))
	(printout t "El paciente tiene gripe" crlf)
)


(defrule sintoma_rubeola
	(fiebre) (escalofrios) (jaqueca) (secrecion)
	=>
	(assert (enfermedad (nombre rubeola)))
	(printout t "El paciente tiene rubeola" crlf)
)

(defrule sintoma_malaria
	(diarrea) (fiebre) (ictericia) (escalofrios)
	=>
	(assert (enfermedad (nombre malaria)))
	(printout t "El paciente tiene malaria" crlf)
)



(defrule sintoma_tuberculosis
	(tos) (cansancio) (fiebre) (escalofrios)
	=>
	(assert (enfermedad (nombre tuberculosis)))
	(printout t "El paciente tiene tuberculosis" crlf)
)

(defrule sintoma_anemia
	(cansancio) (nauseas) (apatia)
	=>
	(assert (enfermedad (nombre anemia)))
	(printout t "El paciente tiene anemia" crlf)
)


(defrule enfermedad_cansancio
	(cansancio)
	(not (exists (enfermedad (nombre gripe)) ))
	(not (exists (enfermedad (nombre rubeola)) ))
	(not (exists (enfermedad (nombre malaria)) ))
	(not (exists (enfermedad (nombre hepatitis)) ))
	(not (exists (enfermedad (nombre tuberculosis)) ))
	(not (exists (enfermedad (nombre anemia)) ))
	=>
	(assert (enfermedad (nombre gripe)))
	(assert (enfermedad (nombre tuberculosis)))
	(assert (enfermedad (nombre anemia)))
	(printout t "El paciente podria tener gripe, tuberculosis, anemia" crlf)
)




(defrule enfermedad_diarrea
	(diarrea)
	(not (exists (enfermedad (nombre gripe)) ))
	(not (exists (enfermedad (nombre rubeola)) ))
	(not (exists (enfermedad (nombre malaria)) ))
	(not (exists (enfermedad (nombre hepatitis)) ))
	(not (exists (enfermedad (nombre tuberculosis)) ))
	(not (exists (enfermedad (nombre anemia)) ))
	
	=>
	(assert (enfermedad (nombre malaria)))
	(assert (enfermedad (nombre hepatitis)))
	(printout t "El paciente podria tener malaria o hepatitis" crlf)
)

(defrule enfermedad_tos
	(tos)
	(not (exists (enfermedad (nombre gripe)) ))
	(not (exists (enfermedad (nombre rubeola)) ))
	(not (exists (enfermedad (nombre malaria)) ))
	(not (exists (enfermedad (nombre hepatitis)) ))
	(not (exists (enfermedad (nombre tuberculosis)) ))
	(not (exists (enfermedad (nombre anemia)) ))
	=>
	(assert (enfermedad (nombre gripe)))
	(assert (enfermedad (nombre tuberculosis)))
	(printout t "El paciente podria tener gripe o tuberculosis" crlf)
)


(defrule enfermedad_fiebre
	(fiebre)
	(not (exists (enfermedad (nombre gripe)) ))
	(not (exists (enfermedad (nombre rubeola)) ))
	(not (exists (enfermedad (nombre malaria)) ))
	(not (exists (enfermedad (nombre hepatitis)) ))
	(not (exists (enfermedad (nombre tuberculosis)) ))
	(not (exists (enfermedad (nombre anemia)) ))
	=>
	(assert (enfermedad (nombre gripe)))
	(assert (enfermedad (nombre rubeola)))
	(assert (enfermedad (nombre malaria)))
	(assert (enfermedad (nombre tuberculosis)))
	(printout t "El paciente podria tener gripe, rubeola, malaria o tuberculosis" crlf)
)

(defrule enfermedad_dolor_de_cabeza
	(dolor_de_cabeza)
	(not (exists (enfermedad (nombre gripe)) ))
	(not (exists (enfermedad (nombre rubeola)) ))
	(not (exists (enfermedad (nombre malaria)) ))
	(not (exists (enfermedad (nombre hepatitis)) ))
	(not (exists (enfermedad (nombre tuberculosis)) ))
	(not (exists (enfermedad (nombre anemia)) ))
	=>
	(assert (enfermedad (nombre gripe)))
	(printout t "El paciente podria tener gripe" crlf)
)

(defrule enfermedad_nauseas
	(nauseas)
	(not (exists (enfermedad (nombre gripe)) ))
	(not (exists (enfermedad (nombre rubeola)) ))
	(not (exists (enfermedad (nombre malaria)) ))
	(not (exists (enfermedad (nombre hepatitis)) ))
	(not (exists (enfermedad (nombre tuberculosis)) ))
	(not (exists (enfermedad (nombre anemia)) ))
	=>
	(assert (enfermedad (nombre hepatitis)))
	(assert (enfermedad (nombre anemia)))
	(printout t "El paciente podria tener hepatitis o anemia" crlf)
)


(defrule enfermedad_escalofrios
	(escalofrios)
	(not (exists (enfermedad (nombre gripe)) ))
	(not (exists (enfermedad (nombre rubeola)) ))
	(not (exists (enfermedad (nombre malaria)) ))
	(not (exists (enfermedad (nombre hepatitis)) ))
	(not (exists (enfermedad (nombre tuberculosis)) ))
	(not (exists (enfermedad (nombre anemia)) ))
	=>
	(assert (enfermedad (nombre rubeola)))
	(assert (enfermedad (nombre malaria)))
	(assert (enfermedad (nombre tuberculosis)))
	(printout t "El paciente podria tener rubeola, malaria o tuberculosis" crlf)
)

(defrule enfermedad_ictericia
	(ictericia)
	(not (exists (enfermedad (nombre gripe)) ))
	(not (exists (enfermedad (nombre rubeola)) ))
	(not (exists (enfermedad (nombre malaria)) ))
	(not (exists (enfermedad (nombre hepatitis)) ))
	(not (exists (enfermedad (nombre tuberculosis)) ))
	(not (exists (enfermedad (nombre anemia)) ))
	=>
	(assert (enfermedad (nombre malaria)))
	(assert (enfermedad (nombre hepatitis)))
	(printout t "El paciente podria tener malaria o hepatitis" crlf)
)

(defrule enfermedad_apatia
	(apatia)
	(not (exists (enfermedad (nombre gripe)) ))
	(not (exists (enfermedad (nombre rubeola)) ))
	(not (exists (enfermedad (nombre malaria)) ))
	(not (exists (enfermedad (nombre hepatitis)) ))
	(not (exists (enfermedad (nombre tuberculosis)) ))
	(not (exists (enfermedad (nombre anemia)) ))
	=>
	(assert (enfermedad (nombre anemia)))
	(printout t "El paciente podria tener anemia" crlf)
)



(defrule enfermedad_jaqueca
	(jaqueca)
	(not (exists (enfermedad (nombre gripe)) ))
	(not (exists (enfermedad (nombre rubeola)) ))
	(not (exists (enfermedad (nombre malaria)) ))
	(not (exists (enfermedad (nombre hepatitis)) ))
	(not (exists (enfermedad (nombre tuberculosis)) ))
	(not (exists (enfermedad (nombre anemia)) ))
	=>
	(assert (enfermedad (nombre rubeola)))
	(printout t "El paciente podria tener rubeola" crlf)
)

(defrule enfermedad_secrecion
	(secrecion)
	(not (exists (enfermedad (nombre gripe)) ))
	(not (exists (enfermedad (nombre rubeola)) ))
	(not (exists (enfermedad (nombre malaria)) ))
	(not (exists (enfermedad (nombre hepatitis)) ))
	(not (exists (enfermedad (nombre tuberculosis)) ))
	(not (exists (enfermedad (nombre anemia)) ))
	=>
	(assert (enfermedad (nombre rubeola)))
	(printout t "El paciente podria tener rubeola" crlf)
)



(defrule tratamiento_malaria
	(enfermedad (nombre malaria))
	=>
	(assert (tratamiento (tipo vacuna)))
	(printout t " " crlf)
	(printout t "Si el paciente tiene  malaria..." crlf)
	(printout t " Se recomienda al paciente un tratamiento de vacunas" crlf)
)


(defrule tratamiento_gripe
	(enfermedad (nombre gripe))
	=>
	(assert (tratamiento (tipo jarabe)))
	(assert (tratamiento (tipo contrex)))
	(assert (tratamiento (tipo vacuna)))
	(printout t " " crlf)
	(printout t "Si el paciente tiene gripe..." crlf)
	(printout t " Se recomienda al paciente un tratamiento de jarabe, contrex o vacunas" crlf)
)

(defrule tratamiento_rubeola
	(enfermedad (nombre rubeola))
	=>
	(assert (tratamiento (tipo vacuna)))
	(assert (tratamiento (tipo pastilla)))
	(printout t " " crlf)
	(printout t "Si el paciente tiene  rubeola..." crlf)
	(printout t " Se recomienda al paciente un tratamiento de vacunas o pastillas" crlf)
)


(defrule tratamiento_hepatitis
	(enfermedad (nombre hepatitis))
	=>
	(assert (tratamiento (tipo vacuna)))
	(assert (tratamiento (tipo pastilla)))
	(printout t " " crlf)
	(printout t "Si el paciente tiene  hepatitis..." crlf)
	(printout t " Se recomienda al paciente un tratamiento de vacunas o pastillas" crlf)
)

(defrule tratamiento_tuberculosis
	(enfermedad (nombre tuberculosis))
	=>
	(assert (tratamiento (tipo pastilla)))
	(printout t " " crlf)
	(printout t "Si el paciente tiene  tuberculosis..." crlf)
	(printout t " Se recomienda al paciente un tratamiento de pastillas" crlf)
)

(defrule tratamiento_anemia
	(enfermedad (nombre anemia))
	=>
	(assert (tratamiento (tipo vitamina)))
	(printout t " " crlf)
	(printout t "Si el paciente tiene  anemia..." crlf)
	(printout t " Se recomienda al paciente un tratamiento de vitaminas" crlf)
)



(defrule medico_vitamina
	(tratamiento (tipo vitamina))
	=>
	(assert (medico (especialidad nutricionista)))
	(printout t "  Si se recomiendan vitaminas: " crlf)
	(printout t "   El paciente debe acudir al nutricionista" crlf)
)

(defrule medico_jarabe
	(tratamiento (tipo jarabe))
	=>
	(assert (medico (especialidad otorrino)))
	(printout t "  Si se recomienda jarabe: " crlf)
	(printout t "   El paciente debe acudir al otorrino" crlf)
)

(defrule medico_contrex
	(tratamiento (tipo contrex))
	=>
	(assert (medico (especialidad otorrino)))
	(printout t "  Si se recomienda contrex: " crlf)
	(printout t "   El paciente debe acudir al otorrino" crlf)
)



(defrule medico_vacuna
	(tratamiento (tipo vacuna))
	=>
	(assert (medico (especialidad endocrinologo)))
	(assert (medico (especialidad medico_general)))
	(printout t "  Si se recomiendan vacunas: " crlf)
	(printout t "   El paciente debe acudir al endocrino o al médico general" crlf)
)

(defrule medico_pastilla
	(tratamiento (tipo pastilla))
	=>
	(assert (medico (especialidad medico_general)))
	(printout t "  Si se recomienda pastillas: " crlf)
	(printout t "   El paciente debe acudir al médico general" crlf)
)