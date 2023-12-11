public class RegresionMethods {

        protected float sumXY (float [][] dataset){
                float resultado = 0;
                for (float[] filas : dataset) resultado += (filas[0] * filas[1]);
                return resultado;
        }

        protected float sumX2 (float [][] dataset){
                float resultado = 0;
                for (float[] filas : dataset) resultado += (filas[1] * filas[1]);
                return resultado;
        }

        protected float sumColumna (float [][] dataset, int columna){
                float resultado = 0;
                for (float[] filas : dataset) resultado += (filas[columna]);
                return resultado;
        }

        protected float coefCorr(float [][] dataset, float mediaX, float mediaY){
                float resultado,
                        prodDividendo = 0, // = S(xi - X) (yi -Y)
                        sumaX_Media = 0,
                        sumaY_Media = 0,
                        divisor = 0;
                //sum xi - mediaX
                for (float[] filas : dataset) {
                        //sum yi - mediaY
                        prodDividendo += ( (filas[1] - mediaX) * (filas[0] - mediaY) );
                        sumaX_Media += ( (filas[1] - mediaX) * (filas[1] - mediaX) );
                        sumaY_Media +=  ( (filas[0] - mediaY) * (filas[0] - mediaY) ) ;
                }

                //S(xi - X) (yi -Y) / √ S(xi - X)² S(yi -Y)²
                divisor = (float) Math.sqrt( (sumaX_Media  ) *  (  sumaY_Media)  );
                resultado = prodDividendo / divisor;
                return  resultado;
        }


        private String numAleatorios (int limite, int cantidad){
                String numAleatorios = " ";
                int numerosGenerados = 0;
                while (numerosGenerados<cantidad){
                        String nuevoNumero= String.valueOf ((int) (Math.random() * limite));
                        if (!numAleatorios.contains(nuevoNumero)) {
                                numAleatorios += " " + nuevoNumero;
                                numerosGenerados ++;
                        }
                }
                //System.out.println("Filas seleccionadas para pruebas del modelo " + numAleatorios);
                return  numAleatorios+" ";
        }

        protected void llenarDataPruebas (float [] [] dataset, float [] [] data70, float [] [] data30, int porc30 ){
                int filaActual30 = 0;
                int filaActual70 = 0;
                String numAleatorios = numAleatorios(dataset.length, porc30);
                System.out.println("Filas descartadas del data set (30%) " + numAleatorios);

                for (int i = 0;i< dataset.length; i++){
                        if (numAleatorios.contains(String.valueOf(" " + i +" "))){
                                float x [] = copiarFila(dataset,i);
                                for (int col30 = 0; col30 < data30[0].length;col30++){
                                        data30 [filaActual30][col30] = x[col30];
                                }
                                filaActual30 ++;
                        }else{
                               //
                                // System.out.println("filaActual70 " + filaActual70);
                                float x [] = copiarFila(dataset,i);
                                for (int col30 = 0; col30 < data30[0].length;col30++){
                                        data70 [filaActual70][col30] = x[col30];
                                }
                                filaActual70 ++;
                        }
                }
        }
        public static void imprimirMatriz(float[][] matriz) {
                System.out.println("Valores de la matriz:");
                for (int i = 0; i < matriz.length; i++) {
                        for (int j = 0; j < matriz[0].length; j++) {
                                System.out.print(matriz[i][j] + "\t");
                        }
                        System.out.println();
                }
        }
        protected float [][] implementacionRLSimple (float [][] dataPrueba, float b1, float b0){
                float [][] resultados = new float[dataPrueba.length][dataPrueba[0].length];
                //columna y = 0, columna x = 1
                float [] valorPredicho = new float[2];

                for (int i = 0; i< dataPrueba.length; i++){
                        valorPredicho [0] = b0 + (dataPrueba[i][1] * b1); //y
                        valorPredicho [1] = dataPrueba [i] [1];
                        resultados [i] [0] = valorPredicho[0];
                        resultados [i] [1] = valorPredicho[1];
                }
                return resultados;
         }
        protected float functionCoefDeter(float [][] dataOriginal, float [][] dataModelo){
                imprimirMatriz(dataModelo);
                float resultado;
                float dividendo = 0, divisor = 0;
                float mediaY = sumColumna(dataOriginal,0)/dataOriginal.length;
                for (int i = 0; i< dataOriginal.length; i++){
                        //(yi - yî) ²
                        dividendo += ((dataOriginal [i][1] - dataModelo [i][0]) * (dataOriginal [i][0] - dataModelo [i][0]));
                        //(yi - Y) ²
                        divisor += ((dataOriginal [i][1] - mediaY) * (dataOriginal [i][0] - mediaY));
                }
                resultado = 1 - (dividendo/divisor);
                return resultado;
        }



        private void divisionValoresDeFila (float [][] matriz,float divisor,int fila){
                for (int i = 0; i < matriz[0].length; i++){
                        matriz[fila][i] /= divisor;
                }
        }

        private void restarAFila (float [][] matriz, float [] filaModificada, int fila){
                for (int i = 0; i < matriz[0].length; i++){
                        matriz[fila][i] -= filaModificada[i];
                }
        }

        protected float [][] functMinversa(float [][] mOriginal){
                float [][] mIdentidad = new float[mOriginal.length][mOriginal[0].length];
                for (int i = 0; i < mIdentidad.length; i++)mIdentidad[i][i] = 1;

                //filaActual = filaPivote, debido a que estamos recorreindo en diagonal
                for (int i = 0; i<mOriginal.length; i ++){
                        if (mOriginal[i][i] != 1){
                                float divisor = mOriginal[i][i];
                                divisionValoresDeFila(mOriginal,divisor,i);
                                divisionValoresDeFila(mIdentidad,divisor,i);
                        }
                        // que no sea la ultima fila
                        if (i != mOriginal.length -1 ){
                                //recorrer elementos a partir de filaActual + 1 = i + 1, con la misma columna i
                                for (int fila = i+1; fila < mOriginal.length; fila ++ ){
                                        float elemento = mOriginal[fila][i];
                                        //para cada elemento, modificar toda su fila -> fila[i] -= fila [i] * elemento
                                        float [] filaPivote = copiarFila(mOriginal, i);
                                        float [] filaPivoteIdentidad = copiarFila(mIdentidad,i);
                                        modificarFilaPorElemento(mOriginal,fila,filaPivote,elemento);
                                        modificarFilaPorElemento(mIdentidad,fila,filaPivoteIdentidad,elemento);
                                }
                        }

                }

                for (int i = mOriginal.length -1; i >= 0; i--){
                        // que no sea la primera fila
                        if (i != 0 ){
                                //recorrer elementos a partir de filaActual - 1 = i - 1, con la misma columna i
                                for (int fila = i-1; fila >= 0; fila -- ){
                                        float elemento = mOriginal[fila][i];
                                        //para cada elemento, modificar toda su fila -> fila[i] -= fila [i] * elemento
                                        float [] filaPivote = copiarFila(mOriginal, i);
                                        float [] filaPivoteIdentidad = copiarFila(mIdentidad,i);
                                        modificarFilaPorElemento2(mOriginal,fila,filaPivote,elemento);
                                        modificarFilaPorElemento2(mIdentidad,fila,filaPivoteIdentidad,elemento);
                                }
                        }

                }

                return  mIdentidad;
        }

        public void modificarFilaPorElemento (float [][ ] matriz, int fila, float [] nuevosValores, float elemento){
                for (int i = 0; i < matriz[0].length; i++){
                        matriz[fila][i] = matriz[fila][i] - ( nuevosValores[i] * elemento);
                }

        }
        public void modificarFilaPorElemento2 (float [][ ] matriz, int fila, float [] nuevosValores, float elemento){
                for (int i =  matriz[0].length -1; i >=0 ; i--){
                        matriz[fila][i] = matriz[fila][i] - ( nuevosValores[i] * elemento);
                }

        }


        public float [] copiarFila(float [][ ] matriz, int fila){
                float [] copia = new float[matriz.length];
                for (int i = 0; i < matriz[0].length; i++){
                        copia[i] = matriz[fila][i];
                }
                return copia;
        }

        public float[][] mTranspuesta(float[][] matriz) {
                float[][] transpuesta = new float[matriz[0].length][matriz.length];

                for (int i = 0; i <  matriz.length; i++)
                        for (int j = 0; j < matriz[0].length; j++) transpuesta[j][i] = matriz[i][j];

                return transpuesta;
        }
        public float[][] multMatrices(float[][] matrix1, float[][] matrix2) {
                float[][] result = new float[matrix1.length][matrix2[0].length];
                for (int i = 0; i < matrix1.length; i++) {
                        for (int j = 0; j < matrix2[0].length; j++) {
                                for (int k = 0; k < matrix1[0].length; k++) {
                                        result[i][j] += matrix1[i][k] * matrix2[k][j];
                                }
                        }
                }

                return result;
        }

        public  void implementacionRLMultiple (float [][] dataOriginal, float b0,float b1, float b2,float b3, float[][] prediccion){
                float suma = b0;
                for (int i = 0 ; i < dataOriginal.length; i++){
                        for (int j = 0; j<dataOriginal[0].length; j++ ){
                                if (j == 1) suma += dataOriginal[i][j] * b1;
                                if (j == 2) suma += dataOriginal[i][j] * b2;
                                if (j == 3) suma += dataOriginal[i][j] * b3;
                        }
                        prediccion[i][0] = suma;
                        suma = b0;
                }

        }
        public  void implementacionRLPol (float [][] dataOriginal, float b0,float b1, float b2,float b3, float[][] prediccion){
                float suma = b0;
                for (int i = 0 ; i < dataOriginal.length; i++){
                        for (int j = 0; j<dataOriginal[0].length; j++ ){
                                if (j == 1) suma += dataOriginal[i][j] * b1;
                                if (j == 2) suma += dataOriginal[i][j] * b2;
                                if (j == 3) suma += dataOriginal[i][j] * b3;
                        }
                        prediccion[i][0] = suma;
                        suma = b0;
                }

        }
       protected float[][] genMatrizNueva(float [][]dataset, int grado){
                float [] [] nuevaMatriz = new float[dataset.length][grado+1];

                for (int i = 0; i< dataset.length;i++){
                        for (int j = 0;j< dataset[0].length; j++){
                                if (j == 0) nuevaMatriz [i][j] = 1;
                                else{
                                        nuevaMatriz [i][j] = dataset[i][j-1];
                                }
                        }
                }

                if (grado == 2){
                        for (int i = 0; i< dataset.length;i++){
                                nuevaMatriz[i][2] = nuevaMatriz[i][1] * nuevaMatriz[i][1];
                        }
                }
               if (grado == 3){
                       for (int i = 0; i< dataset.length;i++){
                               nuevaMatriz[i][2] = nuevaMatriz[i][1] * nuevaMatriz[i][1];
                       }
                       for (int i = 0; i< dataset.length;i++){
                               nuevaMatriz[i][3] = nuevaMatriz[i][1] * nuevaMatriz[i][1]* nuevaMatriz[i][1];
                       }
               }
               return nuevaMatriz;
       }

       public  float[][] divDataEnY( float[][] dataset){
               float[][] dataY = new float[dataset.length][1];
               for (int i = 0; i< dataset.length;i++){
                       dataY[i][0] = dataset[i][1];
               }
               return dataY;
       }

}
