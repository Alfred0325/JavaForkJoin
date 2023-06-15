private void BLAFair_getavoid(boolean[] isFinal, int n, int n_symbols, int[][][] post, int[][] post_len, boolean[][] W, boolean[][] X, int la){
//... code before

ForkJoinPool customThreadPool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());

while(changed_x){
    changed_x=false;

    for(int p=0; p<n; p++)
        for(int q=0; q<n; q++) Y[p][q]=W[p][q];
    
    changed_y=true;
    while(changed_y){
        changed_y=false;
        for(int p=0; p<n; p++) {
            for(int q=0; q<n; q++){
                final int pFinal = p;
                final int qFinal = q;
                customThreadPool.submit( () -> {
                    if(Y[pFinal][qFinal]) return null; // If Y true then stay true
                    if(isFinal[qFinal]) return null; // In getavoid duplicator can't accept, except in W (the part of Y in W is already true; see above)
                    attack[0]=pFinal;
                    if(BLAFair_getavoid_attack(qFinal,isFinal,n_symbols,post,post_len,W,X,Y,la,attack,0))  { 
                        Y[pFinal][qFinal]=true; 
                        changed_y=true; 
                    }
                    return null;
                }).join();
            }
        }
    }

    // X becomes Y, i.e., remove true elements of X that are not true in Y
    for(int p=0; p<n; p++){
        for(int q=0; q<n; q++){
            final int pFinal = p;
            final int qFinal = q;
            customThreadPool.submit( () -> {
                if(X[pFinal][qFinal] && !Y[pFinal][qFinal]) { 
                    X[pFinal][qFinal]=false; 
                    changed_x=true; 
                }
                return null;
            }).join();
        }
    }
}

//... code after
}
