    //region Day22
    @Theory
    public void Day22Part1(@FromDataPoints("example") boolean example) {

        var actual = Day22.Part1.Run(example);

        Assertions.assertEquals(example ? 0:0, actual);
    }
    @Theory
    public void Day22Part2(@FromDataPoints("example") boolean example) {

        var actual = Day22.Part2.Run(example);

        Assertions.assertEquals(example ? 0:0, actual);
    }
    //endregion
}