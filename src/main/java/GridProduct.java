import java.util.Arrays;


public class GridProduct {


    public static int[][] generateGrid(String s) {
        int dim = (int) Math.sqrt(s.split(" ").length);
        int[][] grid = new int[dim][dim];
        for (int i = 0; i < dim; i++)
            for (int j = 0; j < dim; j++)
                grid[i][j] = Integer.parseInt(s.split(" ")[j + dim * i]);
        return grid;
    }

    public static long north(int[][] myGrid, int adj_cells, int row, int col) {
        long product = 1L;
        if (row > adj_cells - 2) {
            for (int i = row; i != row - adj_cells; i--)
                product *= myGrid[i][col];
            return product;
        }
        return -1L;
    }

    public static long south(int[][] myGrid, int adj_cells, int row, int col) {
        long product = 1L;
        int dim = myGrid.length;
        if (row < dim - 3) {
            for (int i = row; i != row + adj_cells; i++)
                product *= myGrid[i][col];
            return product;
        }
        return -1L;
    }

    public static long west(int[][] myGrid, int adj_cells, int row, int col) {
        long product = 1L;
        int dim = myGrid.length;
        if (col > adj_cells - 2) {
            for (int i = col; i != col - adj_cells; i--)
                product *= myGrid[row][i];
            return product;
        }
        return -1L;
    }

    public static long east(int[][] myGrid, int adj_cells, int row, int col) {
        long product = 1L;
        int dim = myGrid.length;
        if (col < dim - 3) {
            for (int i = col; i != col + adj_cells; i++)
                product *= myGrid[row][i];
            return product;
        }
        return -1L;
    }

    public static long north_west(int[][] myGrid, int adj_cells, int row, int col) {
        long product = 1L;
        if (row > adj_cells - 2 && col > adj_cells - 2) {
            for (int i = row, j = col; i != row - adj_cells &&
                    j != col - adj_cells; i--, j--)
                product *= myGrid[i][j];
            return product;
        }
        return -1L;
    }

    public static long north_east(int[][] myGrid, int adj_cells, int row, int col) {
        long product = 1L;
        int dim = myGrid.length;
        if (row > adj_cells - 2 && col < dim - 3) {
            for (int i = row, j = col; i != row - adj_cells &&
                    j != col + adj_cells; i--, j++)
                product *= myGrid[i][j];
            return product;
        }
        return -1L;
    }

    public static long south_west(int[][] myGrid, int adj_cells, int row, int col) {
        long product = 1L;
        int dim = myGrid.length;
        if (row < dim - 3 && col > adj_cells - 2) {
            for (int i = row, j = col; i != row + adj_cells &&
                    j != col - adj_cells; i++, j--)
                product *= myGrid[i][j];
            return product;
        }
        return -1L;
    }

    public static long south_east(int[][] myGrid, int adj_cells, int row, int col) {
        long product = 1L;
        int dim = myGrid.length;
        if (row < dim - 3 && col < dim - 3) {
            for (int i = row, j = col; i != row + adj_cells &&
                    j != col + adj_cells; i++, j++)
                product *= myGrid[i][j];
            return product;
        }
        return -1L;
    }

    public static long cellMaxProduct(int[][] myGrid, int adj_cells, int row, int col) {
        long[] products = new long[8];

        products[0] = north(myGrid, adj_cells, row, col);
        products[1] = south(myGrid, adj_cells, row, col);
        products[2] = west(myGrid, adj_cells, row, col);
        products[3] = east(myGrid, adj_cells, row, col);
        products[4] = north_west(myGrid, adj_cells, row, col);
        products[5] = north_east(myGrid, adj_cells, row, col);
        products[6] = south_west(myGrid, adj_cells, row, col);
        products[7] = south_east(myGrid, adj_cells, row, col);

        Arrays.sort(products);

        return products[products.length - 1];
    }

    public static void calculateLargestProductInGrid(String s, int adj_cells) {
        int[][] myGrid = GridProduct.generateGrid(s);
        long max = Long.MIN_VALUE;
        for(int row = 0; row < myGrid.length; row++)
            for(int col = 0; col < myGrid.length; col++) {
                long cell_prod = GridProduct.cellMaxProduct(myGrid, adj_cells, row, col);
                if (cell_prod > max) max = cell_prod;
            }
        System.out.println(max); // Answer : 70600674 = 87 * 97 * 94 * 89
    }


}
