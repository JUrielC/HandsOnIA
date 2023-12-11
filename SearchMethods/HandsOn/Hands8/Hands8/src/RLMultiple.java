public class RLMultiple extends RegresionMethods {
    float coefCorrelacion;
    float cofDeterminacion;
    float prediccionX;
    float b3;
    float b2;
    float b1;
    float b0;
    float [][] dataSetX;
    float [] [] dataSetY;
    float [] [] data70;
    float [] [] data30;
    float [][] dataPrediccion;
    float [] [] betas;
    RLMultiple(){
        dataSetX = new float[][]  {
                {1,165349.2f, 136897.8f, 471784.1f},
                {1,162597.7f, 151377.59f, 443898.53f},
                {1,153441.51f, 101145.55f, 407934.54f},
                {1,144372.41f, 118671.85f, 383199.62f},
                {1,142107.34f, 91391.77f, 366168.42f},
                {1,131876.9f, 99814.71f, 362861.36f},
                {1,134615.46f, 147198.87f, 127716.82f},
                {1,130298.13f, 145530.06f, 323876.68f},
                {1,120542.52f, 148718.95f, 311613.29f},
                {1,123334.88f, 108679.17f, 304981.62f},
                {1,101913.08f, 110594.11f, 229160.95f},
                {1,100671.96f, 91790.61f, 249744.55f},
                {1,93863.75f, 127320.38f, 249839.44f},
                {1,91992.39f, 135495.07f, 252664.93f},
                {1,119943.24f, 156547.42f, 256512.92f},
                {1,114523.61f, 122616.84f, 261776.23f},
                {1,78013.11f, 121597.55f, 264346.06f},
                {1,94657.16f, 145077.58f, 282574.31f},
                {1,91749.16f, 114175.79f, 294919.57f},
                {1,86419.7f, 153514.11f, 0f},
                {1,76253.86f, 113867.3f, 298664.47f},
                {1,78389.47f, 153773.43f, 299737.29f},
                {1,73994.56f, 122782.75f, 303319.26f},
                {1,67532.53f, 105751.03f, 304768.73f},
                {1,77044.01f, 99281.34f, 140574.81f},
                {1,64664.71f, 139553.16f, 137962.62f},
                {1,75328.87f, 144135.98f, 134050.07f},
                {1,72107.6f, 127864.55f, 353183.81f},
                {1,66051.52f, 182645.56f, 118148.2f},
                {1,65605.48f, 153032.06f, 107138.38f},
                {1,61994.48f, 115641.28f, 91131.24f},
                {1,61136.38f, 152701.92f, 88218.23f},
                {1,63408.86f, 129219.61f, 46085.25f},
                {1,55493.95f, 103057.49f, 214634.81f},
                {1,46426.07f, 157693.92f, 210797.67f},
                {1,46014.02f, 85047.44f, 205517.64f},
                {1,28663.76f, 127056.21f, 201126.82f},
                {1,44069.95f, 51283.14f, 197029.42f},
                {1,20229.59f, 65947.93f, 185265.1f},
                {1,38558.51f, 82982.09f, 174999.3f},
                {1,28754.33f, 118546.05f, 172795.67f},
                {1,27892.92f, 84710.77f, 164470.71f},
                {1,23640.93f, 96189.63f, 148001.11f},
                {1,15505.73f, 127382.3f, 35534.17f},
                {1,22177.74f, 154806.14f, 28334.72f},
                {1,1000.23f, 124153.04f, 1903.93f},
                {1,1315.46f, 115816.21f, 297114.46f},
                {1,0f, 135426.92f, 0f},
                {1,542.05f, 51743.15f, 0f},
                {1,0f, 116983.8f, 45173.06f}

        };
        dataSetY = new float[][]{
                {192261.83f},
                {191792.06f},
                {191050.39f},
                {182901.99f},
                {166187.94f},
                {156991.12f},
                {156122.51f},
                {155752.6f},
                {152211.77f},
                {149759.96f},
                {146121.95f},
                {144259.4f},
                {141585.52f},
                {134307.35f},
                {132602.65f},
                {129917.04f},
                {126992.93f},
                {125370.37f},
                {124266.9f},
                {122776.86f},
                {118474.03f},
                {111313.02f},
                {110352.25f},
                {108733.99f},
                {108552.04f},
                {107404.34f},
                {105733.54f},
                {105008.31f},
                {103282.38f},
                {101004.64f},
                {99937.59f},
                {97483.56f},
                {97427.84f},
                {96778.92f},
                {96712.8f},
                {96479.51f},
                {90708.19f},
                {89949.14f},
                {81229.06f},
                {81005.76f},
                {78239.91f},
                {77798.83f},
                {71498.49f},
                {69758.98f},
                {65200.33f},
                {64926.08f},
                {49490.75f},
                {42559.73f},
                {35673.41f},
                {14681.4f}
        };

        int filas70 = Math.round( (float) (70 * dataSetX.length) /100 ); //70%
        System.out.println(filas70);
        int filas30 = dataSetX.length - filas70; //30%
        System.out.println(filas30);
        data70 = new float[filas70][4];
        data30 = new float[filas30][4];
        llenarDataPruebas(dataSetX,data70,data30,filas30);
        betas = new float[4][1];
        float [][] mInversa = functMinversa(multMatrices(mTranspuesta(dataSetX),dataSetX));
        float [ ][] mMult =multMatrices( mTranspuesta(dataSetX), dataSetY);
        betas = multMatrices(mInversa,mMult);
        imprimirMatriz(betas);
        b0=betas[0][0];
        b1=betas[1][0];
        b2=betas[2][0];
        b3=betas[3][0];

        dataPrediccion = new float[data70.length][1];
        implementacionRLMultiple(data70,b0,b1,b2,b3,dataPrediccion);

        System.out.println("Data 70 ");
        imprimirMatriz(data70);
        System.out.println("Data prediccion");
        imprimirMatriz(dataPrediccion);



    }
    public static void main(String[] args){
        new RLMultiple();
    }
}
