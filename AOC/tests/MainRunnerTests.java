    //region Day20
    @Theory
    public void Day20Part1(@FromDataPoints("example") boolean example) {

        var actual = Day20.Part1.Run(example);

        Assertions.assertEquals(example ? 0:0, actual);
    }
    @Theory
    public void Day20Part2(@FromDataPoints("example") boolean example) {

        var actual = Day20.Part2.Run(example);

        Assertions.assertEquals(example ? 0:0, actual);
    }
    //endregion
}