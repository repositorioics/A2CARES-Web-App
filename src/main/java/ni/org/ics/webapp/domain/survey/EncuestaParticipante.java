package ni.org.ics.webapp.domain.survey;

import java.io.Serializable;
import java.util.Date;

import ni.org.ics.webapp.domain.BaseMetaData;
import ni.org.ics.webapp.domain.core.Participante;
import org.hibernate.annotations.ForeignKey;

import javax.persistence.*;


/**
 * Simple objeto de dominio que representa los datos de la encuesta de los
 * participantes en el estudio
 * @author Miguel Salinas on 6/7/2021
 *
 **/

@Entity
@Table(name = "encuestas_participates", catalog = "a2cares")
public class EncuestaParticipante extends BaseMetaData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private String codigo;
	private Participante participante;
	private Date fechaEncuesta;
	//adultos
	private String emancipado;
	private String descEmancipado;
	private String otraDescEmanc;
	private String embarazada;
	private String conoceFUR;
	private Date fur;
	//niños
	private String asisteEscuela;
	private String gradoEstudia;
	private String nombreEscuela;
	private String turno;
	private String cuidan;
	private Integer cuantosCuidan;
	private String nombreCDI;
	private String direccionCDI;
	private String otroLugarCuidan;

	private String alfabeto;
	private String nivelEducacion;
	private String trabaja;
	private String tipoTrabajo;
	private String ocupacionActual;

	private String personaVive;
	private Integer ordenNac;
	private String padreAlfabeto;
	private String nivelEscolarPadre;
	private String trabajaPadre;
	private String tipoTrabajoPadre;
	private String madreAlfabeta;
	private String nivelEscolarMadre;
	private String trabajaMadre;
	private String tipoTrabajoMadre;
	private String comparteHab;
	private Integer hab1;
	private Integer hab2;
	private Integer hab3;
	private Integer hab4;
	private Integer hab5;
	private String comparteCama;
	private Integer cama1;
	private Integer cama2;
	private Integer cama3;
	private Integer cama4;
	private Integer cama5;
	//MAYORES DE 12
	private String fuma;
	private String periodicidadFuma;
	private Integer cantidadCigarrillos;
	private String fumaDentroCasa;
	//TODOS
	//NIÑOS??
	private String tuberculosisPulmonarActual;
	private Integer anioDiagTubpulActual;
	private String mesDiagTubpulActual;
	private String tratamientoTubpulActual;
	private String completoTratamientoTubpulActual;
	private String tuberculosisPulmonarPasado;
	private String fechaDiagTubpulPasadoDes;
	private Integer anioDiagTubpulPasado;
	private String mesDiagTubpulPasado;
	private String tratamientoTubpulPasado;
	private String completoTratamientoTubpulPas;
	//TODOS
	private String alergiaRespiratoria;
	private Integer anioAlergiaRespiratoria;
	private String asma;
	private Integer anioAsma;
	private String enfermedadCronica;
	private String cardiopatia;
	private Integer anioCardiopatia;
	private String enfermPulmonarObstCronica;
	private Integer anioEnfermPulmonarObstCronica;
	private String diabetes;
	private Integer anioDiabetes;
	private String presionAlta;
	private Integer anioPresionAlta;
	private String otrasEnfermedades;
	private String descOtrasEnfermedades;

	private String tenidoDengue;
	private Integer anioDengue;
	private String diagMedicoDengue;
	private String dondeDengue;
	private String tenidoZika;
	private Integer anioZika;
	private String diagMedicoZika;
	private String dondeZika;
	private String tenidoChik;
	private Integer anioChik;
	private String diagMedicoChik;
	private String dondeChik;
	private String vacunaFiebreAma;
	private Integer anioVacunaFiebreAma;
	private String vacunaCovid;
	private Integer anioVacunaCovid;
	private String mesVacunaCovid;
	private String tieneTarjetaVacuna;

	private String fiebre;
	private String tiempoFiebre;
	private String lugarConsFiebre;
	private String noAcudioCs;
	private String automedicoFiebre;
	private String tenidoDengueUA;
	private String unidadSaludDengue;
	private String centroSaludDengue;
	private String otroCentroSaludDengue;
	private String puestoSaludDengue;
	private String otroPuestoSaludDengue;
	private String hospitalDengue;
	private String otroHospitalDengue;
	private String hospitalizadoDengue;
	private String ambulatorioDengue;
	private String diagnosticoDengue;
	private String rashUA;
	private String recuerdaMesRash;
	private String mesRash;
	private String caraRash;
	private String miembrosSupRash;
	private String toraxRash;
	private String abdomenRash;
	private String miembrosInfRash;
	private String diasRash;
	private String consultaRashUA;
	private String unidadSaludRash;
	private String centroSaludRash;
	private String otroCentroSaludRash;
	private String puestoSaludRash;
	private String otroPuestoSaludRash;
	private String diagnosticoRash;
    private String estudiosAct; // estudios actuales al momento de llenar la encuesta

    @Id
    @Column(name = "CODIGO", nullable = false, length = 50)
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

    @ManyToOne
    @JoinColumn(name = "CODIGO_PARTICIPANTE", nullable = false)
    @ForeignKey(name = "FK_PARTICIPANTE_ENCUESTACASA")
	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

    @Column(name = "FECHA_ENCUESTA", nullable = true)
	public Date getFechaEncuesta() {
		return fechaEncuesta;
	}

	public void setFechaEncuesta(Date fechaEncuesta) {
		this.fechaEncuesta = fechaEncuesta;
	}

    @Column(name = "EMANCIPADO", nullable = true, length = 2)
	public String getEmancipado() {
		return emancipado;
	}

	public void setEmancipado(String emancipado) {
		this.emancipado = emancipado;
	}

    @Column(name = "MOTIVO_EMANCIPADO", nullable = true, length = 3)
	public String getDescEmancipado() {
		return descEmancipado;
	}

	public void setDescEmancipado(String descEmancipado) {
		this.descEmancipado = descEmancipado;
	}

    @Column(name = "OTRO_MOTIVO_EMANCIPADO", nullable = true, length = 255)
	public String getOtraDescEmanc() {
		return otraDescEmanc;
	}

	public void setOtraDescEmanc(String otraDescEmanc) {
		this.otraDescEmanc = otraDescEmanc;
	}

    @Column(name = "EMBARAZADA", nullable = true, length = 2)
	public String getEmbarazada() {
		return embarazada;
	}

	public void setEmbarazada(String embarazada) {
		this.embarazada = embarazada;
	}

    @Column(name = "CONOCE_FUR", nullable = true, length = 2)
	public String getConoceFUR() {
		return conoceFUR;
	}

	public void setConoceFUR(String conoceFUR) {
		this.conoceFUR = conoceFUR;
	}

    @Column(name = "FUR", nullable = true)
	public Date getFur() {
		return fur;
	}

	public void setFur(Date fur) {
		this.fur = fur;
	}

    @Column(name = "ASISTE_ESCUELA", nullable = true, length = 2)
	public String getAsisteEscuela() {
		return asisteEscuela;
	}

	public void setAsisteEscuela(String asisteEscuela) {
		this.asisteEscuela = asisteEscuela;
	}

    @Column(name = "GRADO_ESTUDIA", nullable = true, length = 2)
	public String getGradoEstudia() {
		return gradoEstudia;
	}

	public void setGradoEstudia(String gradoEstudia) {
		this.gradoEstudia = gradoEstudia;
	}

    @Column(name = "NOMBRE_ESCUELA", nullable = true, length = 100)
	public String getNombreEscuela() {
		return nombreEscuela;
	}

	public void setNombreEscuela(String nombreEscuela) {
		this.nombreEscuela = nombreEscuela;
	}

    @Column(name = "TURNO", nullable = true, length = 2)
	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

    @Column(name = "DONDE_CUIDAN", nullable = true, length = 2)
	public String getCuidan() {
		return cuidan;
	}

	public void setCuidan(String cuidan) {
		this.cuidan = cuidan;
	}

    @Column(name = "CUANTOS_CUIDAN", nullable = true)
	public Integer getCuantosCuidan() {
		return cuantosCuidan;
	}

	public void setCuantosCuidan(Integer cuantosCuidan) {
		this.cuantosCuidan = cuantosCuidan;
	}

    @Column(name = "NOMBRE_CDI", nullable = true, length = 128)
	public String getNombreCDI() {
		return nombreCDI;
	}

	public void setNombreCDI(String nombreCDI) {
		this.nombreCDI = nombreCDI;
	}

    @Column(name = "DIRECCION_CDI", nullable = true, length = 255)
	public String getDireccionCDI() {
		return direccionCDI;
	}

	public void setDireccionCDI(String direccionCDI) {
		this.direccionCDI = direccionCDI;
	}

    @Column(name = "OTRO_LUGAR_CUIDAN")
	public String getOtroLugarCuidan() {
		return otroLugarCuidan;
	}

	public void setOtroLugarCuidan(String otroLugarCuidan) {
		this.otroLugarCuidan = otroLugarCuidan;
	}

    @Column(name = "ALFABETO", nullable = true, length = 2)
	public String getAlfabeto() {
		return alfabeto;
	}

	public void setAlfabeto(String alfabeto) {
		this.alfabeto = alfabeto;
	}

    @Column(name = "NIVEL_EDUCACION", nullable = true, length = 2)
	public String getNivelEducacion() {
		return nivelEducacion;
	}

	public void setNivelEducacion(String nivelEducacion) {
		this.nivelEducacion = nivelEducacion;
	}

    @Column(name = "TRABAJA", nullable = true, length = 2)
	public String getTrabaja() {
		return trabaja;
	}

	public void setTrabaja(String trabaja) {
		this.trabaja = trabaja;
	}

    @Column(name = "TIPO_TRABAJO", nullable = true, length = 2)
	public String getTipoTrabajo() {
		return tipoTrabajo;
	}

	public void setTipoTrabajo(String tipoTrabajo) {
		this.tipoTrabajo = tipoTrabajo;
	}

    @Column(name = "OCUPACION_ACTUAL", nullable = true, length = 255)
	public String getOcupacionActual() {
		return ocupacionActual;
	}

	public void setOcupacionActual(String ocupacionActual) {
		this.ocupacionActual = ocupacionActual;
	}

    @Column(name = "CON_QUIEN_VIVE", nullable = true, length = 2)
	public String getPersonaVive() {
		return personaVive;
	}

	public void setPersonaVive(String personaVive) {
		this.personaVive = personaVive;
	}

    @Column(name = "ORDEN_NACIMIENTO", nullable = true)
	public Integer getOrdenNac() {
		return ordenNac;
	}

	public void setOrdenNac(Integer ordenNac) {
		this.ordenNac = ordenNac;
	}

    @Column(name = "PADRE_ALFABETO", nullable = true, length = 2)
	public String getPadreAlfabeto() {
		return padreAlfabeto;
	}

	public void setPadreAlfabeto(String padreAlfabeto) {
		this.padreAlfabeto = padreAlfabeto;
	}

    @Column(name = "NIVEL_ESCOLAR_PADRE", nullable = true, length = 2)
	public String getNivelEscolarPadre() {
		return nivelEscolarPadre;
	}

	public void setNivelEscolarPadre(String papaNivel) {
		this.nivelEscolarPadre = papaNivel;
	}

    @Column(name = "TRABAJA_PADRE", nullable = true, length = 2)
	public String getTrabajaPadre() {
		return trabajaPadre;
	}

	public void setTrabajaPadre(String trabajaPadre) {
		this.trabajaPadre = trabajaPadre;
	}

    @Column(name = "TIPO_TRABAJO_PADRE", nullable = true, length = 2)
	public String getTipoTrabajoPadre() {
		return tipoTrabajoPadre;
	}

	public void setTipoTrabajoPadre(String papaTipoTra) {
		this.tipoTrabajoPadre = papaTipoTra;
	}

    @Column(name = "MADRE_ALFABETA", nullable = true, length = 2)
	public String getMadreAlfabeta() {
		return madreAlfabeta;
	}

	public void setMadreAlfabeta(String madreAlfabeta) {
		this.madreAlfabeta = madreAlfabeta;
	}

    @Column(name = "NIVEL_ESCOLAR_MADRE", nullable = true, length = 2)
	public String getNivelEscolarMadre() {
		return nivelEscolarMadre;
	}

	public void setNivelEscolarMadre(String mamaNivel) {
		this.nivelEscolarMadre = mamaNivel;
	}

    @Column(name = "TRABAJA_MADRE", nullable = true, length = 2)
	public String getTrabajaMadre() {
		return trabajaMadre;
	}

	public void setTrabajaMadre(String trabajaMadre) {
		this.trabajaMadre = trabajaMadre;
	}

    @Column(name = "TIPO_TRABAJO_MADRE", nullable = true, length = 2)
	public String getTipoTrabajoMadre() {
		return tipoTrabajoMadre;
	}

	public void setTipoTrabajoMadre(String mamaTipoTra) {
		this.tipoTrabajoMadre = mamaTipoTra;
	}

    @Column(name = "COMPARTE_HABITACION", nullable = true, length = 2)
	public String getComparteHab() {
		return comparteHab;
	}

	public void setComparteHab(String comparteHab) {
		this.comparteHab = comparteHab;
	}

    @Column(name = "HABITACION_1", nullable = true)
	public Integer getHab1() {
		return hab1;
	}

	public void setHab1(Integer hab1) {
		this.hab1 = hab1;
	}

    @Column(name = "HABITACION_2", nullable = true)
	public Integer getHab2() {
		return hab2;
	}

	public void setHab2(Integer hab2) {
		this.hab2 = hab2;
	}

    @Column(name = "HABITACION_3", nullable = true)
	public Integer getHab3() {
		return hab3;
	}

	public void setHab3(Integer hab3) {
		this.hab3 = hab3;
	}

    @Column(name = "HABITACION_4", nullable = true)
	public Integer getHab4() {
		return hab4;
	}

	public void setHab4(Integer hab4) {
		this.hab4 = hab4;
	}

    @Column(name = "HABITACION_5", nullable = true)
	public Integer getHab5() {
		return hab5;
	}

	public void setHab5(Integer hab5) {
		this.hab5 = hab5;
	}

    @Column(name = "COMPARTE_CAMA", nullable = true, length = 2)
	public String getComparteCama() {
		return comparteCama;
	}

	public void setComparteCama(String comparteCama) {
		this.comparteCama = comparteCama;
	}

    @Column(name = "CAMA_1", nullable = true)
	public Integer getCama1() {
		return cama1;
	}

	public void setCama1(Integer cama1) {
		this.cama1 = cama1;
	}

    @Column(name = "CAMA_2", nullable = true)
	public Integer getCama2() {
		return cama2;
	}

	public void setCama2(Integer cama2) {
		this.cama2 = cama2;
	}

    @Column(name = "CAMA_3", nullable = true)
	public Integer getCama3() {
		return cama3;
	}

	public void setCama3(Integer cama3) {
		this.cama3 = cama3;
	}

    @Column(name = "CAMA_4", nullable = true)
	public Integer getCama4() {
		return cama4;
	}

	public void setCama4(Integer cama4) {
		this.cama4 = cama4;
	}

    @Column(name = "CAMA_5", nullable = true)
	public Integer getCama5() {
		return cama5;
	}

	public void setCama5(Integer cama5) {
		this.cama5 = cama5;
	}

    @Column(name = "FUMA", nullable = true, length = 2)
	public String getFuma() {
		return fuma;
	}

	public void setFuma(String fuma) {
		this.fuma = fuma;
	}

    @Column(name = "PERIODICIDAD_FUNA", nullable = true, length = 2)
	public String getPeriodicidadFuma() {
		return periodicidadFuma;
	}

	public void setPeriodicidadFuma(String periodicidadFuma) {
		this.periodicidadFuma = periodicidadFuma;
	}

    @Column(name = "CANTIDAD_CIGARRILLOS", nullable = true)
	public Integer getCantidadCigarrillos() {
		return cantidadCigarrillos;
	}

	public void setCantidadCigarrillos(Integer cantidadCigarrillos) {
		this.cantidadCigarrillos = cantidadCigarrillos;
	}

    @Column(name = "FUMA_DENTRO_CASA", nullable = true, length = 2)
	public String getFumaDentroCasa() {
		return fumaDentroCasa;
	}

	public void setFumaDentroCasa(String fumaDentroCasa) {
		this.fumaDentroCasa = fumaDentroCasa;
	}

    @Column(name = "TUBERCULOSIS_PULMONAR_ACTUAL", nullable = true, length = 2)
	public String getTuberculosisPulmonarActual() {
		return tuberculosisPulmonarActual;
	}

	public void setTuberculosisPulmonarActual(String tuberculosisPulmonarActual) {
		this.tuberculosisPulmonarActual = tuberculosisPulmonarActual;
	}

    @Column(name = "ANIO_TUBPUL_ACTUAL", nullable = true)
	public Integer getAnioDiagTubpulActual() {
		return anioDiagTubpulActual;
	}

	public void setAnioDiagTubpulActual(Integer anioDiagTubpulActual) {
		this.anioDiagTubpulActual = anioDiagTubpulActual;
	}

    @Column(name = "MES_TUBPUL_ACTUAL", nullable = true, length = 2)
	public String getMesDiagTubpulActual() {
		return mesDiagTubpulActual;
	}

	public void setMesDiagTubpulActual(String mesDiagTubpulActual) {
		this.mesDiagTubpulActual = mesDiagTubpulActual;
	}

    @Column(name = "TRATAMIENTO_TUBPUL_ACTUAL", nullable = true, length = 2)
	public String getTratamientoTubpulActual() {
		return tratamientoTubpulActual;
	}

	public void setTratamientoTubpulActual(String tratamientoTubpulActual) {
		this.tratamientoTubpulActual = tratamientoTubpulActual;
	}

    @Column(name = "COMPLETO_TRATAMIENTO_TUBPUL_ACTUAL", nullable = true, length = 2)
	public String getCompletoTratamientoTubpulActual() {
		return completoTratamientoTubpulActual;
	}

	public void setCompletoTratamientoTubpulActual(String completoTratamientoTubpulActual) {
		this.completoTratamientoTubpulActual = completoTratamientoTubpulActual;
	}

    @Column(name = "TUBERCULOSIS_PULMONAR_PASADO", nullable = true, length = 2)
	public String getTuberculosisPulmonarPasado() {
		return tuberculosisPulmonarPasado;
	}

	public void setTuberculosisPulmonarPasado(String tuberculosisPulmonarPasado) {
		this.tuberculosisPulmonarPasado = tuberculosisPulmonarPasado;
	}

    @Column(name = "CONOCE_FECHA_DIAG_TUBPUL_PASADO", nullable = true, length = 2)
	public String getFechaDiagTubpulPasadoDes() {
		return fechaDiagTubpulPasadoDes;
	}

	public void setFechaDiagTubpulPasadoDes(String fechaDiagTubpulPasadoDes) {
		this.fechaDiagTubpulPasadoDes = fechaDiagTubpulPasadoDes;
	}

    @Column(name = "ANIO_TUBPUL_PASADO", nullable = true)
	public Integer getAnioDiagTubpulPasado() {
		return anioDiagTubpulPasado;
	}

	public void setAnioDiagTubpulPasado(Integer anioDiagTubpulPasado) {
		this.anioDiagTubpulPasado = anioDiagTubpulPasado;
	}

    @Column(name = "MES_TUBPUL_PASADO", nullable = true, length = 2)
	public String getMesDiagTubpulPasado() {
		return mesDiagTubpulPasado;
	}

	public void setMesDiagTubpulPasado(String mesDiagTubpulPasado) {
		this.mesDiagTubpulPasado = mesDiagTubpulPasado;
	}

    @Column(name = "TRATAMIENTO_TUBPUL_PASADO", nullable = true, length = 2)
	public String getTratamientoTubpulPasado() {
		return tratamientoTubpulPasado;
	}

	public void setTratamientoTubpulPasado(String tratamientoTubpulPasado) {
		this.tratamientoTubpulPasado = tratamientoTubpulPasado;
	}

    @Column(name = "COMPLETO_TRATAMIENTO_TUBPUL_PAS", nullable = true, length = 2)
	public String getCompletoTratamientoTubpulPas() {
		return completoTratamientoTubpulPas;
	}

	public void setCompletoTratamientoTubpulPas(String completoTratamientoTubpulPas) {
		this.completoTratamientoTubpulPas = completoTratamientoTubpulPas;
	}

    @Column(name = "ALERGIA_RESPIRATORIA", nullable = true, length = 2)
	public String getAlergiaRespiratoria() {
		return alergiaRespiratoria;
	}

	public void setAlergiaRespiratoria(String alergiaRespiratoria) {
		this.alergiaRespiratoria = alergiaRespiratoria;
	}

    @Column(name = "ANIO_ALERGIA_RESPIRATORIA", nullable = true)
	public Integer getAnioAlergiaRespiratoria() {
		return anioAlergiaRespiratoria;
	}

	public void setAnioAlergiaRespiratoria(Integer anioAlergiaRespiratoria) {
		this.anioAlergiaRespiratoria = anioAlergiaRespiratoria;
	}

    @Column(name = "ASMA", nullable = true, length = 2)
	public String getAsma() {
		return asma;
	}

	public void setAsma(String asma) {
		this.asma = asma;
	}

    @Column(name = "ANIO_ASMA", nullable = true)
	public Integer getAnioAsma() {
		return anioAsma;
	}

	public void setAnioAsma(Integer anioAsma) {
		this.anioAsma = anioAsma;
	}

    @Column(name = "ENFERMEDAD_CRONICA", nullable = true, length = 2)
	public String getEnfermedadCronica() {
		return enfermedadCronica;
	}

	public void setEnfermedadCronica(String enfermedadCronica) {
		this.enfermedadCronica = enfermedadCronica;
	}

    @Column(name = "CARDIOPATIA", nullable = true, length = 2)
	public String getCardiopatia() {
		return cardiopatia;
	}

	public void setCardiopatia(String cardiopatia) {
		this.cardiopatia = cardiopatia;
	}

    @Column(name = "ANIO_CARDIOPATIA", nullable = true)
	public Integer getAnioCardiopatia() {
		return anioCardiopatia;
	}

	public void setAnioCardiopatia(Integer anioCardiopatia) {
		this.anioCardiopatia = anioCardiopatia;
	}

    @Column(name = "ENFERM_PULMONAR_OBST_CRONICA", nullable = true, length = 2)
	public String getEnfermPulmonarObstCronica() {
		return enfermPulmonarObstCronica;
	}

	public void setEnfermPulmonarObstCronica(String enfermPulmonarObstCronica) {
		this.enfermPulmonarObstCronica = enfermPulmonarObstCronica;
	}

    @Column(name = "ANIO_EPOC", nullable = true)
	public Integer getAnioEnfermPulmonarObstCronica() {
		return anioEnfermPulmonarObstCronica;
	}

	public void setAnioEnfermPulmonarObstCronica(Integer anioEnfermPulmonarObstCronica) {
		this.anioEnfermPulmonarObstCronica = anioEnfermPulmonarObstCronica;
	}

    @Column(name = "DIABETES", nullable = true, length = 2)
	public String getDiabetes() {
		return diabetes;
	}

	public void setDiabetes(String diabetes) {
		this.diabetes = diabetes;
	}

    @Column(name = "ANIO_DIABETES", nullable = true)
	public Integer getAnioDiabetes() {
		return anioDiabetes;
	}

	public void setAnioDiabetes(Integer anioDiabetes) {
		this.anioDiabetes = anioDiabetes;
	}

    @Column(name = "PRESION_ALTA", nullable = true, length = 2)
	public String getPresionAlta() {
		return presionAlta;
	}

	public void setPresionAlta(String presionAlta) {
		this.presionAlta = presionAlta;
	}

    @Column(name = "ANIO_PRESION_ALTA", nullable = true)
	public Integer getAnioPresionAlta() {
		return anioPresionAlta;
	}

	public void setAnioPresionAlta(Integer anioPresionAlta) {
		this.anioPresionAlta = anioPresionAlta;
	}

    @Column(name = "OTRAS_ENFERMEDADES", nullable = true, length = 2)
	public String getOtrasEnfermedades() {
		return otrasEnfermedades;
	}

	public void setOtrasEnfermedades(String otrasEnfermedades) {
		this.otrasEnfermedades = otrasEnfermedades;
	}

    @Column(name = "DESC_OTRAS_ENFERMEDADES", nullable = true, length = 255)
	public String getDescOtrasEnfermedades() {
		return descOtrasEnfermedades;
	}

	public void setDescOtrasEnfermedades(String descOtrasEnfermedades) {
		this.descOtrasEnfermedades = descOtrasEnfermedades;
	}

    @Column(name = "TENIDO_DENGUE", nullable = true, length = 2)
	public String getTenidoDengue() {
		return tenidoDengue;
	}

	public void setTenidoDengue(String tenidoDengue) {
		this.tenidoDengue = tenidoDengue;
	}

    @Column(name = "ANIO_DENGUE", nullable = true)
	public Integer getAnioDengue() {
		return anioDengue;
	}

	public void setAnioDengue(Integer anioDengue) {
		this.anioDengue = anioDengue;
	}

    @Column(name = "MEDICO_DIAG_DENGUE", nullable = true, length = 2)
	public String getDiagMedicoDengue() {
		return diagMedicoDengue;
	}

	public void setDiagMedicoDengue(String diagMedicoDengue) {
		this.diagMedicoDengue = diagMedicoDengue;
	}

    @Column(name = "DONDE_DIAG_DENGUE", nullable = true, length = 2)
	public String getDondeDengue() {
		return dondeDengue;
	}

	public void setDondeDengue(String dondeDengue) {
		this.dondeDengue = dondeDengue;
	}

    @Column(name = "TENIDO_ZIKA", nullable = true, length = 2)
	public String getTenidoZika() {
		return tenidoZika;
	}

	public void setTenidoZika(String tenidoZika) {
		this.tenidoZika = tenidoZika;
	}

    @Column(name = "ANIO_ZIKA", nullable = true)
	public Integer getAnioZika() {
		return anioZika;
	}

	public void setAnioZika(Integer anioZika) {
		this.anioZika = anioZika;
	}

    @Column(name = "MEDICO_DIAG_ZIKA", nullable = true, length = 2)
	public String getDiagMedicoZika() {
		return diagMedicoZika;
	}

	public void setDiagMedicoZika(String diagMedicoZika) {
		this.diagMedicoZika = diagMedicoZika;
	}

    @Column(name = "DONDE_DIAG_ZIKA", nullable = true, length = 2)
	public String getDondeZika() {
		return dondeZika;
	}

	public void setDondeZika(String dondeZika) {
		this.dondeZika = dondeZika;
	}

    @Column(name = "TENIDO_CHIK", nullable = true, length = 2)
	public String getTenidoChik() {
		return tenidoChik;
	}

	public void setTenidoChik(String tenidoChik) {
		this.tenidoChik = tenidoChik;
	}

    @Column(name = "ANIO_CHIK", nullable = true)
	public Integer getAnioChik() {
		return anioChik;
	}

	public void setAnioChik(Integer anioChik) {
		this.anioChik = anioChik;
	}

    @Column(name = "MEDICO_DIAG_CHIK", nullable = true, length = 2)
	public String getDiagMedicoChik() {
		return diagMedicoChik;
	}

	public void setDiagMedicoChik(String diagMedicoChik) {
		this.diagMedicoChik = diagMedicoChik;
	}

    @Column(name = "DONDE_DIAG_CHIK", nullable = true, length = 2)
	public String getDondeChik() {
		return dondeChik;
	}

	public void setDondeChik(String dondeChik) {
		this.dondeChik = dondeChik;
	}

    @Column(name = "VACUNA_FIEBRE_AMARILLA", nullable = true, length = 2)
	public String getVacunaFiebreAma() {
		return vacunaFiebreAma;
	}

	public void setVacunaFiebreAma(String vacunaFiebreAma) {
		this.vacunaFiebreAma = vacunaFiebreAma;
	}

    @Column(name = "ANIO_VAC_FIEBRE_AMARILLA", nullable = true)
	public Integer getAnioVacunaFiebreAma() {
		return anioVacunaFiebreAma;
	}

	public void setAnioVacunaFiebreAma(Integer anioVacunaFiebreAma) {
		this.anioVacunaFiebreAma = anioVacunaFiebreAma;
	}

    @Column(name = "VACUNA_COVID", nullable = true, length = 2)
	public String getVacunaCovid() {
		return vacunaCovid;
	}

	public void setVacunaCovid(String vacunaCovid) {
		this.vacunaCovid = vacunaCovid;
	}

    @Column(name = "ANIO_VACUNA_COVID", nullable = true)
	public Integer getAnioVacunaCovid() {
		return anioVacunaCovid;
	}

	public void setAnioVacunaCovid(Integer anioVacunaCovid) {
		this.anioVacunaCovid = anioVacunaCovid;
	}

    @Column(name = "MES_VACUNA_COVID", nullable = true, length = 2)
	public String getMesVacunaCovid() {
		return mesVacunaCovid;
	}

	public void setMesVacunaCovid(String mesVacunaCovid) {
		this.mesVacunaCovid = mesVacunaCovid;
	}

    @Column(name = "TIENE_TARJETA_VACUNA", nullable = true, length = 2)
	public String getTieneTarjetaVacuna() {
		return tieneTarjetaVacuna;
	}

	public void setTieneTarjetaVacuna(String tieneTarjetaVacuna) {
		this.tieneTarjetaVacuna = tieneTarjetaVacuna;
	}

    @Column(name = "TENIDO_FIEBRE_2M", nullable = true, length = 1)
	public String getFiebre() {
		return fiebre;
	}

	public void setFiebre(String fiebre) {
		this.fiebre = fiebre;
	}

    @Column(name = "DURACION_FIEBRE_2M", nullable = true, length = 1)
	public String getTiempoFiebre() {
		return tiempoFiebre;
	}

	public void setTiempoFiebre(String tiempoFiebre) {
		this.tiempoFiebre = tiempoFiebre;
	}

    @Column(name = "LUGAR_CONSULTA_FIEBRE_2M", nullable = true, length = 1)
	public String getLugarConsFiebre() {
		return lugarConsFiebre;
	}

	public void setLugarConsFiebre(String lugarConsFiebre) {
		this.lugarConsFiebre = lugarConsFiebre;
	}

    @Column(name = "NO_ACUDIO_CS_FIEBRE_2M", nullable = true)
	public String getNoAcudioCs() {
		return noAcudioCs;
	}

	public void setNoAcudioCs(String noAcudioCs) {
		this.noAcudioCs = noAcudioCs;
	}

    @Column(name = "AUTOMEDICO_FIEBRE_2M", nullable = true)
	public String getAutomedicoFiebre() {
		return automedicoFiebre;
	}

	public void setAutomedicoFiebre(String automedicoFiebre) {
		this.automedicoFiebre = automedicoFiebre;
	}

    @Column(name = "TENIDO_DENGUE_UA", nullable = true, length = 2)
	public String getTenidoDengueUA() {
		return tenidoDengueUA;
	}

	public void setTenidoDengueUA(String tenidoDengueUA) {
		this.tenidoDengueUA = tenidoDengueUA;
	}

    @Column(name = "UNIDAD_SALUD_DENGUE_UA", nullable = true, length = 2)
	public String getUnidadSaludDengue() {
		return unidadSaludDengue;
	}

	public void setUnidadSaludDengue(String unidadSaludDengue) {
		this.unidadSaludDengue = unidadSaludDengue;
	}

    @Column(name = "CENTRO_SALUD_DENGUE_UA", nullable = true, length = 2)
	public String getCentroSaludDengue() {
		return centroSaludDengue;
	}

	public void setCentroSaludDengue(String centroSaludDengue) {
		this.centroSaludDengue = centroSaludDengue;
	}

    @Column(name = "OTRO_CENTRO_SALUD_DENGUE_UA")
	public String getOtroCentroSaludDengue() {
		return otroCentroSaludDengue;
	}

	public void setOtroCentroSaludDengue(String otroCentroSaludDengue) {
		this.otroCentroSaludDengue = otroCentroSaludDengue;
	}

    @Column(name = "PUESTO_SALUD_DENGUE_UA", nullable = true, length = 2)
	public String getPuestoSaludDengue() {
		return puestoSaludDengue;
	}

	public void setPuestoSaludDengue(String puestoSaludDengue) {
		this.puestoSaludDengue = puestoSaludDengue;
	}

    @Column(name = "OTRO_PUESTO_SALUD_DENGUE_UA")
	public String getOtroPuestoSaludDengue() {
		return otroPuestoSaludDengue;
	}

	public void setOtroPuestoSaludDengue(String otroPuestoSaludDengue) {
		this.otroPuestoSaludDengue = otroPuestoSaludDengue;
	}

    @Column(name = "HOSPITAL_DENGUE_UA", nullable = true, length = 3)
	public String getHospitalDengue() {
		return hospitalDengue;
	}

	public void setHospitalDengue(String hospitalDengue) {
		this.hospitalDengue = hospitalDengue;
	}

    @Column(name = "OTRO_HOSPITAL_DENGUE_UA")
	public String getOtroHospitalDengue() {
		return otroHospitalDengue;
	}

	public void setOtroHospitalDengue(String otroHospitalDengue) {
		this.otroHospitalDengue = otroHospitalDengue;
	}

    @Column(name = "HOSPITALIZADO_DENGUE_UA", nullable = true, length = 2)
	public String getHospitalizadoDengue() {
		return hospitalizadoDengue;
	}

	public void setHospitalizadoDengue(String hospitalizadoDengue) {
		this.hospitalizadoDengue = hospitalizadoDengue;
	}

    @Column(name = "AMBULATORIO_DENGUE_UA", nullable = true, length = 2)
	public String getAmbulatorioDengue() {
		return ambulatorioDengue;
	}

	public void setAmbulatorioDengue(String ambulatorioDengue) {
		this.ambulatorioDengue = ambulatorioDengue;
	}

    @Column(name = "DIAGNOSTICO_DENGUE_UA")
	public String getDiagnosticoDengue() {
		return diagnosticoDengue;
	}

	public void setDiagnosticoDengue(String diagnosticoDengue) {
		this.diagnosticoDengue = diagnosticoDengue;
	}

    @Column(name = "TENIDO_RASH_UA", nullable = true, length = 2)
	public String getRashUA() {
		return rashUA;
	}

	public void setRashUA(String rashUA) {
		this.rashUA = rashUA;
	}

    @Column(name = "RECUERDA_MES_RASH_UA", nullable = true, length = 2)
	public String getRecuerdaMesRash() {
		return recuerdaMesRash;
	}

	public void setRecuerdaMesRash(String recuerdaMesRash) {
		this.recuerdaMesRash = recuerdaMesRash;
	}

    @Column(name = "MES_RASH_UA", nullable = true, length = 2)
	public String getMesRash() {
		return mesRash;
	}

	public void setMesRash(String mesRash) {
		this.mesRash = mesRash;
	}

    @Column(name = "CARA_RASH_UA", nullable = true, length = 2)
	public String getCaraRash() {
		return caraRash;
	}

	public void setCaraRash(String caraRash) {
		this.caraRash = caraRash;
	}

    @Column(name = "MIEMBROS_SUP_RASH_UA", nullable = true, length = 2)
	public String getMiembrosSupRash() {
		return miembrosSupRash;
	}

	public void setMiembrosSupRash(String miembrosSupRash) {
		this.miembrosSupRash = miembrosSupRash;
	}

    @Column(name = "TORAX_RASH_UA", nullable = true, length = 2)
	public String getToraxRash() {
		return toraxRash;
	}

	public void setToraxRash(String toraxRash) {
		this.toraxRash = toraxRash;
	}

    @Column(name = "ABDOMEN_RASH_UA", nullable = true, length = 2)
	public String getAbdomenRash() {
		return abdomenRash;
	}

	public void setAbdomenRash(String abdomenRash) {
		this.abdomenRash = abdomenRash;
	}

    @Column(name = "MIEMBROS_INF_RASH_UA", nullable = true, length = 2)
	public String getMiembrosInfRash() {
		return miembrosInfRash;
	}

	public void setMiembrosInfRash(String miembrosInfRash) {
		this.miembrosInfRash = miembrosInfRash;
	}

    @Column(name = "DIAS_RASH_UA", nullable = true, length = 2)
	public String getDiasRash() {
		return diasRash;
	}

	public void setDiasRash(String diasRash) {
		this.diasRash = diasRash;
	}

    @Column(name = "CONSULTA_RASH_UA", nullable = true, length = 2)
	public String getConsultaRashUA() {
		return consultaRashUA;
	}

	public void setConsultaRashUA(String consultaRashUA) {
		this.consultaRashUA = consultaRashUA;
	}

    @Column(name = "UNIDAD_SALUD_RASH_UA", nullable = true, length = 2)
	public String getUnidadSaludRash() {
		return unidadSaludRash;
	}

	public void setUnidadSaludRash(String unidadSaludRash) {
		this.unidadSaludRash = unidadSaludRash;
	}

    @Column(name = "CENTRO_SALUD_RASH_UA", nullable = true, length = 2)
	public String getCentroSaludRash() {
		return centroSaludRash;
	}

	public void setCentroSaludRash(String centroSaludRash) {
		this.centroSaludRash = centroSaludRash;
	}

    @Column(name = "OTRO_CENTRO_SALUD_RASH_UA")
	public String getOtroCentroSaludRash() {
		return otroCentroSaludRash;
	}

	public void setOtroCentroSaludRash(String otroCentroSaludRash) {
		this.otroCentroSaludRash = otroCentroSaludRash;
	}

    @Column(name = "PUESTO_SALUD_RASH_UA", nullable = true, length = 2)
	public String getPuestoSaludRash() {
		return puestoSaludRash;
	}

	public void setPuestoSaludRash(String puestoSaludRash) {
		this.puestoSaludRash = puestoSaludRash;
	}

    @Column(name = "OTRO_PUESTO_SALUD_RASH_UA", nullable = true)
	public String getOtroPuestoSaludRash() {
		return otroPuestoSaludRash;
	}

	public void setOtroPuestoSaludRash(String otroPuestoSaludRash) {
		this.otroPuestoSaludRash = otroPuestoSaludRash;
	}

    @Column(name = "DIAGNOSTICO_RASH_UA")
	public String getDiagnosticoRash() {
		return diagnosticoRash;
	}

	public void setDiagnosticoRash(String diagnosticoRash) {
		this.diagnosticoRash = diagnosticoRash;
	}

    @Column(name = "ESTUDIOS_ACTUALES", nullable = true, length = 2)
	public String getEstudiosAct() {
		return estudiosAct;
	}

	public void setEstudiosAct(String estudiosAct) {
		this.estudiosAct = estudiosAct;
	}
}
