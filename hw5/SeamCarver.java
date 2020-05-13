import edu.princeton.cs.algs4.Picture;

import java.util.LinkedList;

public class SeamCarver {
    private Picture picture;
    public SeamCarver(Picture picture) {
        this.picture = new Picture(picture) ;
    }
    public Picture picture() {
        return picture;
    }
    public     int width() {
        return picture.width();
    }
    public     int height() {
        return picture.height();
    }
    public double energy(int x, int y) {
        if (x < 0 || x >= picture.width() || y < 0 || y >= picture.height()) {
            throw new java.lang.IndexOutOfBoundsException("out of bounds");
        }
        double xEnergy, yEnergy;
        if (x == 0) {
            xEnergy = xEnergyHelper(1, picture.width() - 1, y);
        } else if (x == picture.width() - 1) {
            xEnergy = xEnergyHelper(picture.width() - 2, 0, y);
        } else {
            xEnergy = xEnergyHelper(x + 1, x - 1, y);
        }

        if (y == 0) {
            yEnergy = yEnergyHelper(1, picture.height() - 1, x);
        } else if (y == picture.height() - 1) {
            yEnergy = yEnergyHelper(picture.height() - 2, 0, x);
        } else {
            yEnergy = yEnergyHelper(y + 1, y - 1, x);
        }
        return xEnergy + yEnergy;
    }
    private double xEnergyHelper(int x1, int x2, int y) {
        return  Math.pow(picture.get(x1,y).getRed() - picture.get(x2,y).getRed(), 2)
                + Math.pow(picture.get(x1,y).getGreen() - picture.get(x2,y).getGreen(), 2)
                + Math.pow(picture.get(x1,y).getBlue() - picture.get(x2,y).getBlue(), 2);
    }
    private double yEnergyHelper(int y1, int y2, int x) {
        return  Math.pow(picture.get(x,y1).getRed() - picture.get(x,y2).getRed(), 2)
                + Math.pow(picture.get(x,y1).getGreen() - picture.get(x,y2).getGreen(), 2)
                + Math.pow(picture.get(x,y1).getBlue() - picture.get(x,y2).getBlue(), 2);
    }
    public int[] findHorizontalSeam(){
        Picture TransposePicture = new Picture(this.picture.height(), this.picture.width());
        for (int x = 0; x < this.picture.width(); x++) {
            for (int y = 0; y < this.picture.height(); y++) {
                TransposePicture.set(y, x, this.picture.get(x, y));
            }
        }
        SeamCarver TransposeSearCarver = new SeamCarver(TransposePicture);
        return TransposeSearCarver.findVerticalSeam();
    }

    public int[] findVerticalSeam(){
        LinkedList<Integer>[] preAllSeam= new LinkedList[picture.width()];
        LinkedList<Integer>[] AllSeam= new LinkedList[picture.width()];
        for (int i = 0; i < picture.width(); i++) {
            preAllSeam[i] = new LinkedList<>();
        }
        double[] preEnergy = new double[picture.width()];
        double[] totalEnergy = new double[picture.width()];
        int minPreIndex;
        int minTotalIndex = 0;
        int[] Seam = new int[picture.height()];
        for (int x = 0; x < picture.width(); x++) {
            preEnergy[x] = energy(x, 0);
            totalEnergy[x] = energy(x, 0);
            preAllSeam[x].add(x);
        }
        for (int y = 1; y < picture.height(); y++) {
            for (int x = 0; x < picture.width(); x++) {
                AllSeam[x] = new LinkedList<>();
                minPreIndex = minPreEnergyIndex(x, preEnergy);
                totalEnergy[x] = energy(x, y) + preEnergy[minPreIndex];
                AllSeam[x].addAll(preAllSeam[minPreIndex]);
                AllSeam[x].add(x);
            }
            for (int x = 0; x < picture.width(); x++) {
                preAllSeam[x] = new LinkedList<>();
                preAllSeam[x].addAll(AllSeam[x]);
            }
            System.arraycopy(totalEnergy, 0, preEnergy, 0, picture.width());
        }
        for (int x = 0; x < picture.width(); x++) {
            minTotalIndex = totalEnergy[minTotalIndex] < totalEnergy[x] ? minTotalIndex : x;
        }
        for (int i = 0; i < Seam.length; i++) {
            Seam[i] = AllSeam[minTotalIndex].removeFirst();
        }
        return Seam;
    }
    private int minPreEnergyIndex(int x, double[] preEnergy) {
        if (x == 0) {
            return preEnergy[0] < preEnergy[1] ? 0 : 1;
        } else if (x == picture.width() - 1) {
            return preEnergy[picture.width() - 1] < preEnergy[picture.width() - 2] ? picture.width() - 1 : picture.width() - 2;
        } else {
            return threeMinIndex(x - 1, x, x + 1, preEnergy);
        }
    }
    private int threeMinIndex(int a, int b, int c, double[] preEnergy) {
        int minIndex;
        minIndex = preEnergy[a] < preEnergy[b] ? a : b;
        minIndex = preEnergy[minIndex] < preEnergy[c] ? minIndex : c;
        return minIndex;
    }

    public void removeHorizontalSeam(int[] seam) {
        SeamRemover.removeHorizontalSeam(picture, seam);
    }
    public void removeVerticalSeam(int[] seam){
        SeamRemover.removeVerticalSeam(picture, seam);
    }

}