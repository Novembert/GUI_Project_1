package gui.norbert_bujny.projekt1;

public abstract class GenericMenuCreator<T> {
        protected T receiver;

        GenericMenuCreator(T receiver) {
            this.receiver = receiver;
        }

        public abstract MenuList createMenuList();
}
