
class IdentToken extends Token {

    private String name;

    public IdentToken(String s) {
        super(TokenType.IDENT);
        name = s;
    }

    @Override
    String getName() {
        return name;
    }
}
