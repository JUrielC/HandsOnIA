import java.util.ArrayList;

public class RLSimple extends RegresionMethods {
    float coefCorrelacion;
    float cofDeterminacion;
    float prediccionX;
    float b1;
    float b0;
    float [][] dataSet;
    float [] [] data70;
    float [] [] data30;
    float [][] dataPrediccion;
    RLSimple(float valorX ){
        dataSet = new float[][]{
                {651, 23},
                {762, 26},
                {856, 30},
                {1063, 34},
                {1190, 43},
                {1298, 48},
                {1421, 52},
                {1440, 57},
                {1518, 58}};
        int filas70 = Math.round( (float) (70 * dataSet.length) /100 ); //70%
        int filas30 = dataSet.length - filas70; //30%
        data70 = new float[filas70][2];
        data30 = new float[filas30][2];
        llenarDataPruebas(dataSet,data70,data30,filas30);
        //betas
        float sumaXY = sumXY(data70),
                cuadSumX = sumX2(data70),
                sumaColumnaX =  sumColumna(data70, 1),
                sumaColumnaY = sumColumna(data70,0),
                mediaColumnaX = sumaColumnaX / data70.length,
                mediaColumnaY = sumaColumnaY / data70.length;

        b1 = ( sumaXY - (sumaColumnaX * sumaColumnaY / data70.length))
                / ( cuadSumX - (sumaColumnaX *sumaColumnaX / data70.length));
        b0 = (mediaColumnaY - b1 * mediaColumnaX);

        //coeficiente de correlación
        coefCorrelacion = coefCorr(data70, mediaColumnaX, mediaColumnaY);
        cofDeterminacion = functionCoefDeter(data70,implementacionRLSimple(data70,b1,b0));
        prediccionX = b0 + (b1 * valorX);
        dataPrediccion = implementacionRLSimple(data30,b1,b0);

    }

    public static void main (String [] args){
        RLSimple mejorInstancia;
        float valorX = Float.parseFloat( args [0]);

        RLSimple prueba1 = new RLSimple(valorX);
        System.out.println("Coeficiente de determinacion prueba 1: " + prueba1.cofDeterminacion);
        System.out.println();
        mejorInstancia = prueba1;

        RLSimple prueba2 = new RLSimple(valorX);
        System.out.println("Coeficiente de determinacion prueba 2: " + prueba2.cofDeterminacion);
        System.out.println();
        if (prueba2.cofDeterminacion> mejorInstancia.cofDeterminacion) mejorInstancia = prueba2;


        RLSimple prueba3 = new RLSimple(valorX);
        System.out.println("Coeficiente de determinacion prueba 3: " + prueba3.cofDeterminacion);
        System.out.println();
        if (prueba3.cofDeterminacion> mejorInstancia.cofDeterminacion) mejorInstancia = prueba3;

        System.out.println("Mayor coeficiente de determinacion :" + mejorInstancia.cofDeterminacion);
        System.out.println("Ecuacion de regresion: Y = " + mejorInstancia.b0 + " + " + mejorInstancia.b1 + "X");
        System.out.println("Valor Y para el valor X de entrada: " + mejorInstancia.prediccionX);

        System.out.println("Beta 0 " + mejorInstancia.b0);
        System.out.println("Beta 1 " + mejorInstancia.b1);
        System.out.println("Coeficiente de Correlacion  " + mejorInstancia.coefCorrelacion);
        System.out.println("Coeficiente de determinacion " + mejorInstancia.cofDeterminacion);
        System.out.println("Dataset original");
        imprimirMatriz(mejorInstancia.dataSet);
        System.out.println("Dataset usado (70%):");
        imprimirMatriz(mejorInstancia.data70);
        System.out.println("Dataset para pruebas (30%):");
        imprimirMatriz(mejorInstancia.data30);
        System.out.println("Predicciones del modelo para el 30% del data set ");
        imprimirMatriz(mejorInstancia.dataPrediccion);




    }

}
