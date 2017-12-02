public interface IGameObject {
    boolean checkEdible();
    void accept(IVisitor visitor);
}
