package com.study.search;

public class BlockSearchDemo {
    public static void main(String[] args) {
        int[] arr = {16, 5, 9, 12, 21, 18,
                32, 23, 37, 26, 45, 34,
                50, 48, 61, 52, 73, 66
        };
        BlockIndex b1 = new BlockIndex(21, 0, 5);
        BlockIndex b2 = new BlockIndex(45, 6, 11);
        BlockIndex b3 = new BlockIndex(73, 12, 17);

        BlockIndex[] blockIndex = {b1, b2, b3};

        int number = 66;//要查找的元素
        int index = getIndex(blockIndex, arr, number);
        System.out.println(index);

    }

    public static int getIndex(BlockIndex[] blockArr, int[] arr, int number) {
        int blockNumber = getNumber(blockArr, number);
        if (blockNumber == -1){
            return -1;
        }
        for (int i = blockArr[blockNumber].getStartIndex(); i <= blockArr[blockNumber].getEndIndex(); i++) {
            if (number == arr[i]){
                return i;
            }
        }
        return -1;
    }

    public static int getNumber(BlockIndex[] blockIndex, int number) {
        for (int i = 0; i < blockIndex.length; i++) {
            if (number < blockIndex[i].getMax()) {
                return i;
            }
        }
        return -1;
    }
}

class BlockIndex {
    private int max;
    private int startIndex;
    private int endIndex;

    public BlockIndex() {
    }

    public BlockIndex(int max, int startIndex, int endIndex) {
        this.max = max;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    /**
     * 获取
     *
     * @return max
     */
    public int getMax() {
        return max;
    }

    /**
     * 设置
     *
     * @param max
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * 获取
     *
     * @return startIndex
     */
    public int getStartIndex() {
        return startIndex;
    }

    /**
     * 设置
     *
     * @param startIndex
     */
    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * 获取
     *
     * @return endIndex
     */
    public int getEndIndex() {
        return endIndex;
    }

    /**
     * 设置
     *
     * @param endIndex
     */
    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public String toString() {
        return "BlockIndex{max = " + max + ", startIndex = " + startIndex + ", endIndex = " + endIndex + "}";
    }
}
