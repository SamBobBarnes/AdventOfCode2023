    //region Day18
    @Theory
    public void Day18Part1(@FromDataPoints("example") boolean example) {

        var actual = Day18.Part1.Run(example);

        Assertions.assertEquals(example ? 0:0, actual);
    }
    @Theory
    public void Day18Part2(@FromDataPoints("example") boolean example) {

        var actual = Day18.Part2.Run(example);

        Assertions.assertEquals(example ? 0:0, actual);
    }
    //endregion
}