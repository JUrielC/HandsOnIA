import java.text.DecimalFormat;

public class RPolinomial extends  RegresionMethods {

    float [] [] data70;
    float [] [] data30;
    float b3;
    float b2;
    float b1;
    float b0;
    RPolinomial(){

        float[][] datos = {
                {180, 95},
                {115, 96},
                {106, 95},
                {97, 97},
                {95, 93},
                {91, 94},
                {97, 95},
                {83, 93},
                {83, 92},
                {78, 86},
                {54, 73},
                {67, 80},
                {56, 65},
                {53, 69},
                {61, 77},
                {115, 96},
                {81, 87},
                {78, 89},
                {30, 60},
                {45, 63},
                {99, 95},
                {32, 61},
                {25, 55},
                {28, 56},
                {90, 94},
                {89, 93}
        };
        //int grado = 1;
        float[][] matrizGrado1 ;
        float[][] matrizGrado2 ;
        float[][] matrizGrado3 ;
        float [][] dataY ;
        int filas70 = Math.round( (float) (70 * datos.length) /100 ); //70%
        int filas30 = datos.length - filas70; //30%


        dataY = divDataEnY(datos);



        data70 = new float[filas70][2];
        data30 = new float[filas30][2];
        llenarDataPruebas(datos,data70,data30,filas30);

        //matriz grado1

        matrizGrado1 = genMatrizNueva(data70,1);
        matrizGrado2 = genMatrizNueva(data70,2);
        matrizGrado3 = genMatrizNueva(data70,3);

       /* System.out.println("Matriz grado 1:");
        imprimirMatriz(matrizGrado1);
        System.out.println("Matriz grado 2:");
        imprimirMatriz(matrizGrado2);
        System.out.println("Matriz grado 3:");
        imprimirMatriz(matrizGrado3);
        imprimirMatriz(dataY);
        System.out.println();*/

        float [][] mInversa = functMinversa(multMatrices(mTranspuesta(matrizGrado1),matrizGrado1));
        float [ ][] mMult =multMatrices( mTranspuesta(matrizGrado1), dataY);
        float [][] betasGrado1;
        betasGrado1 = multMatrices(mInversa,mMult);
        /*System.out.println("Betas de grado 1: ");
        imprimirMatriz(betasGrado1);*/
        System.out.println(betasGrado1[0][0] + " + " + betasGrado1[1][0] +"X ");


        /*System.out.println("Matriz grado 1:");
        imprimirMatriz(matrizGrado1);*/

        mInversa = functMinversa(multMatrices(mTranspuesta(matrizGrado2),matrizGrado2));
        mMult =multMatrices( mTranspuesta(matrizGrado2), dataY);
        float [][] betasGrado2;
        betasGrado2 = multMatrices(mInversa,mMult);
        /*System.out.println("Betas de grado 2: ");
        imprimirMatriz(betasGrado2);
        System.out.println();*/
        System.out.println(betasGrado2[0][0] + " + " + betasGrado2[1][0] +"X " + " + " + betasGrado2[2][0] +"X^2 ");

        DecimalFormat formato = new DecimalFormat("#.##########"); // Especifica el formato decimal deseado

        // Convertir el número al formato decimal deseado



        mInversa = functMinversa(multMatrices(mTranspuesta(matrizGrado3),matrizGrado3));
        mMult =multMatrices( mTranspuesta(matrizGrado3), dataY);
        float [][] betasGrado3;
        betasGrado3 = multMatrices(mInversa,mMult);
        /*System.out.println("Betas de grado 3: ");
        imprimirMatriz(betasGrado3);*/
        System.out.println(betasGrado3[0][0] + " + " + betasGrado3[1][0] +"X " + " + " + formato.format( betasGrado3[2][0]) +"X^2 "+ " + " + formato.format( betasGrado3[3][0]) +"X^3 ");

        float [][] prediccionG1 = new float[data30.length][1];
        float [][] prediccionG2 = new float[data30.length][1];
        float [][] prediccionG3 = new float[data30.length][1];


        System.out.println("Valores de prueba: 30% del data set");
        imprimirMatriz(data30);
        implementacionRLMultiple(data30,betasGrado1[0][0],betasGrado1[1][0],b2,b3,prediccionG1);
        implementacionRLMultiple(data30,betasGrado2[0][0],betasGrado2[1][0],betasGrado2[2][0],b3,prediccionG2);
        implementacionRLMultiple(data30,betasGrado3[0][0],betasGrado3[1][0],betasGrado3[2][0],betasGrado3[3][0],prediccionG3);

        System.out.println("Valores predichos por los modelos\nGrado 1:");
        imprimirMatriz(prediccionG1);
        System.out.println("Grado 2:");
        imprimirMatriz(prediccionG2);
        System.out.println("Grado 3:");
        imprimirMatriz(prediccionG3);



        //imprimirMatriz(matrizGrado3);


    }
    public static void main (String [] args){
        new RPolinomial();

    }
}
