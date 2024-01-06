import java.util.*;

public class _1780{

    static int[][] map;
    static int __1;
    static int _0;
    static int _1;

    public static void main(String[] args){
    
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        
        map = new int[n][n];
        for(int i = 0; i<n ;i++){
            for(int j = 0; j<n; j++){
                map[i][j] = sc.nextInt();
            }
        }
        
        slice(0,0,n);
        
        
        
    
        System.out.println(__1);
        System.out.println(_0);
        System.out.println(_1);
        
        sc.close();
        
    }
    
    public static void slice(int r, int c, int n){
        if(sameCheck(r,c,n)){
            if(map[r][c] == -1){
                __1++;
            }
            else if(map[r][c] == 0){
                _0++;
            }
            else{
                _1++;
            }
            return;
        }
        
        int sliceSize = n/3;
        slice(r,c,sliceSize);
        slice(r,c + sliceSize,sliceSize);
        slice(r,c + 2 * sliceSize,sliceSize);
        
        slice(r + sliceSize,c,sliceSize);
        slice(r + sliceSize,c + sliceSize,sliceSize);
        slice(r + sliceSize,c + 2 * sliceSize,sliceSize);
        
        slice(r + 2 * sliceSize,c,sliceSize);
        slice(r + 2 * sliceSize,c + sliceSize,sliceSize);
        slice(r + 2 * sliceSize,c + 2 * sliceSize,sliceSize);
        
    }
    
    public static boolean sameCheck(int r, int c, int n){
        int target = map[r][c];
        for(int i = r; i<r+n ;i++){
            for(int j = c; j<c+n; j++){
                if(map[i][j] != target){
                    return false;
                }
            }
        }
        return true;
    }
}
