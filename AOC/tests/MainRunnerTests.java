    //region Day24
    @Theory
    public void Day24Part1(@FromDataPoints("example") boolean example) {

        var actual = Day24.Part1.Run(example);

        Assertions.assertEquals(example ? 0:0, actual);
    }
    @Theory
    public void Day24Part2(@FromDataPoints("example") boolean example) {

        var actual = Day24.Part2.Run(example);

        Assertions.assertEquals(example ? 0:0, actual);
    }
    //endregion
}