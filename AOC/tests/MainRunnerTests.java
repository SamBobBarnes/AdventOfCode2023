    //region Day25
    @Theory
    public void Day25Part1(@FromDataPoints("example") boolean example) {

        var actual = Day25.Part1.Run(example);

        Assertions.assertEquals(example ? 0:0, actual);
    }
    @Theory
    public void Day25Part2(@FromDataPoints("example") boolean example) {

        var actual = Day25.Part2.Run(example);

        Assertions.assertEquals(example ? 0:0, actual);
    }
    //endregion
}