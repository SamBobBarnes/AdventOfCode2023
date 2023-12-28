    //region Day19
    @Theory
    public void Day19Part1(@FromDataPoints("example") boolean example) {

        var actual = Day19.Part1.Run(example);

        Assertions.assertEquals(example ? 0:0, actual);
    }
    @Theory
    public void Day19Part2(@FromDataPoints("example") boolean example) {

        var actual = Day19.Part2.Run(example);

        Assertions.assertEquals(example ? 0:0, actual);
    }
    //endregion
}