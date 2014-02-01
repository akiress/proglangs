class IdentToken extends Token {
  private String name;

  public IdentToken(String s) {
    super(TokenType.IDENT);
    name = s;
  }

  String getName() {
    return name;
  }
}
