    //region Day21
    @Theory
    public void Day21Part1(@FromDataPoints("example") boolean example) {

        var actual = Day21.Part1.Run(example);

        Assertions.assertEquals(example ? 0:0, actual);
    }
    @Theory
    public void Day21Part2(@FromDataPoints("example") boolean example) {

        var actual = Day21.Part2.Run(example);

        Assertions.assertEquals(example ? 0:0, actual);
    }
    //endregion
}