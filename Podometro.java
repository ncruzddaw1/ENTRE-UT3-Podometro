/**
 * La clase modela un sencillo podómetro que registra información
 * acerca de los pasos, distancia, ..... que una persona (hombre o mujer)
 * ha dado en una semana. 
 * 
 * @author    - pon aquí tu nombre - 
 * 
 */
public class Podometro {
    //Constantes
    private final char HOMBRE = 'H';
    private final char MUJER = 'M';
    private final double ZANCADA_HOMBRE = 0.45;
    private final double ZANCADA_MUJER = 0.41;
    private final int SABADO = 6;
    private final int DOMINGO = 7;
    //Atributos o variables de instancias 
    private String marca;
    private double altura;
    private char sexo;
    private double longitudZancada;
    private int totalPasosLaborables;
    private int totalPasosSabado;
    private int totalPasosDomingo;
    private int totalDistanciaSemana;
    private int totalDistanciaFinSemana;
    private int tiempo;
    
    private int caminatasNoche;

    /**
     * Inicializa el podómetro con la marca indicada por el parámetro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca)
    {
        marca = queMarca;
        altura = 0;
        sexo = MUJER;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }

    /**
     * accesor para la marca
     *  
     */
    public String getMarca()
    {
        return marca;
    }

    /**
     * Simula la configuración del podómetro.
     * Recibe como parámetros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna además el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo) 
    {
        altura = queAltura;
        sexo = queSexo;
        if(queSexo == HOMBRE)
        {
            longitudZancada = (double) (Math.ceil(queAltura * ZANCADA_HOMBRE) / 100);
        }
        else if (queSexo == MUJER)
        {
            longitudZancada = (double)(Math.floor(queAltura * ZANCADA_MUJER) / 100);
        }

    }

    /**
     *  Recibe cuatro parámetros que supondremos correctos:
     *    pasos - el nº de pasos caminados
     *    dia - nº de día de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - Sábado, 7 - Domingo)
     *    horaInicio – hora de inicio de la caminata
     *    horaFina – hora de fin de la caminata
     *    
     *    A partir de estos parámetros el método debe realizar ciertos cálculos
     *    y  actualizará el podómetro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,int horaFin) 
    {
        double totalDistancia = pasos * longitudZancada;
        totalDistanciaSemana += totalDistancia;
        switch(dia)
        {
            case 1:
            case 2:
            break; 
            case 3:
            case 4:
            case 5: totalPasosLaborables += pasos;
            break;
            case 6: totalPasosSabado += pasos;
            totalDistanciaFinSemana += totalDistancia;
            break;
            case 7: totalPasosDomingo += pasos;
            totalDistanciaFinSemana += totalDistancia;
        }
        
        if(horaInicio >= caminatasNoche)
        {
            caminatasNoche++;
        }
        
        int horas = horaInicio / 100 - horaFin % 100;
        int minutos = horaInicio / 100  - horaFin % 100;    
        tiempo += horas * 60 + minutos;
    }

    /**
     * Muestra en pantalla la configuración del podómetro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() 
    {
        double queAltura = altura / 100;
        String queSexo;
        if(sexo == 'H' )
        {
            queSexo = "HOMBRE";
        }
        else
        {
            queSexo = "Mujer";
        }
        System.out.println("Configuracion del podometro" + "\n***************************" +
                "\nsexo: " + queSexo + "\nAltura: " + queAltura + "\nlongitud Zancada: " + longitudZancada +" mtos");
    }

    /**
    * Muestra en pantalla información acerca de la distancia recorrida,
    * pasos, tiempo total caminado, ....
    * 
    * (leer enunciado)
    *  
    */
    public void printEstadísticas() 
    {
        int horas = tiempo / 60;
        int minutos = tiempo % 60;
        System.out.println("Estadisticas" + "\n***************************" + 
                           "\nDistancia recorrida toda la semana: " + totalDistanciaSemana + " Km"+ 
                           "\nDistancia recorrida fin de semana: " + totalDistanciaFinSemana + " Km" +
                           "\n" + "\nPasos dias laborables: " + totalPasosLaborables + 
                           "\nNº Pasos SABADO: " + totalPasosSabado + "\nNº Pasos DOMINGO: " + totalPasosDomingo +
                           "\n" + "\nNº Caminatas realizadas a partir de las 21h: " + caminatasNoche + 
                           "\n" + "\nTiempo total caminado en la semana: " + horas + "h. y" + minutos + "m.");
    }

    /**
    *  Calcula y devuelve un String que representa el nombre del día
    *  en el que se ha caminado más pasos - "SÁBADO"   "DOMINGO" o  "LABORABLES"
    */
    public String diaMayorNumeroPasos()
    {
        String numeroPasos;
        if(totalPasosLaborables > totalPasosSabado && totalPasosLaborables > totalPasosSabado)
        {
            numeroPasos = "LABORABLES";
        }
        else if(totalPasosSabado > totalPasosDomingo)
        {
            numeroPasos = "SABADO";
        }
        else 
        {
            numeroPasos = "DOMINGO";
        }
        return numeroPasos;
    }
    
    /**
    * Restablecer los valores iniciales del podómetro
    * Todos los atributos se ponen a cero salvo el sexo
    * que se establece a MUJER. La marca no varía
    *  
    */    
    public void reset() 
    {
        altura = 0;
        sexo = MUJER;
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;
    }
}
