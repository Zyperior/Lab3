package sample;

public class ObserverDemo {

    public static void main(String[] args) {

        StringSubject observableString = new StringSubject();
        Observer observer = new Observer() {
            @Override
            public void listener() {
                System.out.println("StringSubject changed value: " + observableString.getField());
            }
        };

        observableString.registerObserver(observer);

        observableString.registerObserver(()-> System.out.println("Another observer: " + observableString.getField()));

        observableString.setField("Ny text");
        observableString.setField("Ny text");
        observableString.setField("Nyare text");

        observableString.unRegisterObserver(observer);

        observableString.setField("More text");




    }
}
