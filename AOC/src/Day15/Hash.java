package Day15;

import static java.lang.Integer.parseInt;

class Hash
{
    public static int parseString(String string) {
        var chars = string.toCharArray();
        long value = 0;
        long multiplyBy = 17;

        for(var character: chars) {
            value += character;
            value *= multiplyBy;
        }

        return (int)(value % 256);
    }

    public static int parseString2(String string) {
        var chars = string.split("[=\\-]")[0].toCharArray();
        long value = 0;
        long multiplyBy = 17;

        for(var character: chars) {
            value += character;
            value *= multiplyBy;
        }

        return (int)(value % 256);
    }

    public static Lens parseStringToLens(String string) {
        var chars = string.toCharArray();
        var hash = parseString2(string);
        var label = string.split("[=\\-]")[0];
        var insert = chars[chars.length-2] == '=';
        if(insert) {
            var lensValue = parseInt(string.split("[=\\-]")[1]);
            return new Lens(label, true, hash, lensValue);
        }
        return new Lens(label, false, hash, 0);

    }
}
