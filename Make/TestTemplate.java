    //region Day${DAY}
    @Theory
    public void Day${DAY}Part1(@FromDataPoints("example") boolean example) {

        int actual = Day${DAY}.Part1.Run(example);

        Assertions.assertEquals(example ? 0:0, actual);
    }
    @Theory
    public void Day${DAY}Part2(@FromDataPoints("example") boolean example) {

        int actual = Day${DAY}.Part2.Run(example);

        Assertions.assertEquals(example ? 0:0, actual);
    }
    //endregion
}