public class Main extends RegresionMethods {



    public static void main(String[] args) {
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
        RegresionMethods r = new RegresionMethods();

        imprimirMatriz(r.divDataEnY(datos));
    }
}