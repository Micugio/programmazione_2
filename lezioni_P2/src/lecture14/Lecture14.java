package lecture14;

import lecture05.inheritance.*;
import lecture14.examples.*;

//@ # Lezione 14
public class Lecture14 {
    public static void main(String[] args) {
        //@ BT: Understand, Analyse, Evaluate
        System.out.println("---------------- Generics ----------------");
        try {
            rawTypeExample();
        }catch (RuntimeException e){}
        genericsExample();
        //@ BT: Understand, Analyse, Evaluate
        System.out.println("---------------- Invariance, Variance, Co-Variance ----------------");
        invarianceExample();
        try{
            covarianceExample();
        }catch (ArrayStoreException e){}
        contravarianceExample();
        System.out.println();
        //@ BT: Understand, Analyse
        System.out.println("---------------- Wildcards ----------------");
        covarianceExtendsExample();
        contravarianceSuperExample();
        pecsExample();
    }

    //@ ## Senza Generics: Object
    //@ Vediamo la classe `RawSpawner`, dove settiamo una entita` (di tipo`Object`) e poi la possiamo spawnare
    //@ Una volta che otteniamo l'entita`, non sappiamo altro che non sia Object
    //@ Quindi per usarla dobbiamo fare dei cast, che possono essere problematici
    public static void rawTypeExample() {
        RawSpawner mySpawner = new RawSpawner();
        mySpawner.setEntity(new Zombie());

        Object o = mySpawner.spawn();
        Creeper c = (Creeper) o;
        c.hiss();
        //QUIZ: Cosa succede quando invochiamo `c . h i s s ()` ?
        //@ ---
    }

    //@ Per esempio, in `rawTypeExample`, facciamo un cast convinti di aver messo un `Creeper` nello spawner, ed il codice scoppia

    //@ ## Generics
    //@ Vediamo la classe `Spawner`.
    //@ Questa classe usa una notazione nuova: introduce un parametro di Tipo tra parentesi angolari.
    //@ Voi siete abituati a definire parametri formali nelle funzioni, e a passare valori come parametri attuali all'invocazione delle funzioni
    //@ L'idea dei parametri di tipo e` analoga
    //@ Certe classi (o anche certi metodi) hanno dei parametri di tipo
    //@ Quando vengono istanziate, questi parametri vanno istanziati a loro volta con dei tipi specifici
    //@ La differenza coi parametri formali e` che questi ultimi venivano istanziati con dei valori, mentre i parametri di tipo vengono istanziati coi dei tipi

    //@ Proprio come i parametri formali, i parametri di tipo hanno uno scope
    //@ Nel caso siano definiti a livello di classe, lo scope dei parametri di tipo e` tutta la classe
    //@ Nel caso siano definiti in un metodo, lo scope e` il metodo stesso
    //@ Quindi in `Spawner` noi possiamo usare il tipo `T` all'interno della classe
    //@ Per esempio per un campo, come `entity`
    //@ Oppure come parametro di un metodo, oppure come tipo di ritorno di un metodo

    //@ I Generici forniscono al linguaggio la proprieta` di Polimorfismo parametrico
    //@ Purtroppo, Java implementa male i generici -- non e` nato con questa feature
    //@ Quindi non otteniamo tutti i benefici del polimorfismo parametrico: parametricity
    //@ Pero` e` una ottima feature, e in futuro parleremo anche di parametricity e di cosa significa per un linguaggio avere questa proprieta`

    //@ #### Come usiamo il parametro di tipo?
    //@ Non abbiamo informazioni su `T` all'interno di `Spawner`
    //@ Non sappiamo come si possa comportare
    //@ Quindi dobbiamo trattare `T` in modo 'opaco'
    //@ Per non trattarlo in modo opaco, dobbiamo introdurre dei `bounds`, che vediamo piu` avanti

    //@ #### Come usiamo una classe con un generico?
    //@ Dentro il metodo `genericsExample` usiamo lo `Spawner`.
    //@ Il tipo statico dello spawner deve necessariamente dichiarare il tipo con cui istanziare `T`
    //@ Il costruttore puo` evitare di indicare il tipo: per una volta Java fa type inference
    //QUIZ: Considerate `z o m b i e S p a w n e r` : `S p a w n e r < Z o m b i e >`.
    // Che tipo ha `s e t E n t i t y`?
    // Che tipo ha quello che ritorna `z o m b i e S p a w n e r . s p a w n ()`?
    //@ ---

    private static void genericsExample() {
        Spawner<Zombie> zombieSpawner = new Spawner<>();
        zombieSpawner.setEntity(new Zombie());
        Zombie z = zombieSpawner.spawn();
        z.groan();

        Spawner<Creeper> creeperSpawner = new Spawner<>();
        creeperSpawner.setEntity(new Creeper(0));
        creeperSpawner.spawn().hiss();

        //QUIZ: Queste righe compilano?
//        creeperSpawner.setEntity(new Creeper(0));
//        creeperSpawner.setEntity(new Zombie());
//        creeperSpawner.spawn().groan();
//        zombieSpawner.spawn().groan();
    }
    //@ ---
    //@ I generici ci impediscono di fare errori di tipo: quello che c'e` dentro un `Spawner<Zombie>` e` staticamente conosciuto come qualcosa di tipo `Zombie`

    //@ ## Generici e invarianza
    //@ I generici non sono una feature di Java di partenza, sono stati aggiunti
    //@ Ora vediamo una cosa non particolarmente piacevole: i generici sono tipati secondo 'invarianza'
    //@ Esistono diversi approcci per gestire il polimorfismo di sottotipo in un linguaggio che ha anche tipi composti (o polimorfismo parametrico)
    //@ Questi approcci hanno il nome di varianza, ed includono: invarianza, covarianza e controvarianza

    //@ Supponiamo che `Zombie` is-a `Entity.
    //QUIZ: Cosa possiamo dire?
    // Spawner<Zombie> is-a Spawner<Entity>
    // Spawner<Entity> is-a Spawner<Zombie>
    // Spawner<Zombie> is-a Spawner<Zombie>
    //@ ---

    //@ Una cosa intuitiva sarebbe concludere che `Spawner<Zombie>` 'is-a' `Spawner<Entity>`
    //@ Questa si chiama covarianza
    //@ Il metodo `invarianceExample` ci fa vedere che se avessimo covarianza, potremmo avere errori di tipo a runtime, senza che il compilatore se ne accorga
    //@ Con covarianza, potremmo creare uno `Spawner<Zombie`, dargli tipo statico `Spawner<Entity>` e metterci dentro un `Creeper`
    private static void invarianceExample() {
        Spawner<Zombie> zombieSpawner = new Spawner<>();
        // Spawner<Entity> genericSpawner = zombieSpawner;
        // genericSpawner.setEntity(new Creeper());
    }

    //@ ## Array e Covarianza
    //@ Gli array di Java so covarianti, e questo e` un problema
    //@ Infatti, `Zombie[]` e` considerato un sottotipo di `Entity[]`
    //@ Il metodo `covarianceExample` genera un runtime crash
    //@ Prima crea un array di `Zombie`, poi tramite covarianza lo assegna a un array di `Entity`, poi tramite subtyping mettee un `Creeper` in questo array: non si puo`!
    private static void covarianceExample() {
        Zombie[] zombieArray = new Zombie[1];
        Entity[] entityArray = zombieArray;
        entityArray[0] = new Creeper(5);
    }

    //@ ## Controvarianza (come concetto)
    //@ Controvarianza e` l'opposto della varianza: se `Zombie` is-a `Entity` allora `Spawner<Entity>` is-a `Spawner<Zombie>`
    //@ Questa cosa puo` sembrare controintutiva ma e` utile per quando dobbiamo scrivere dati e creare cosiddetti consumers

    //@ #### Dettagli di covarianza e controvarianza
    //@ Capiamo i dettagli di quando funzionano covarianza e controvarianza perche` Java li permette con le wildcards
    //@ Il metodo `contravarianceExample` ha diverse righe commentate perche` Java non ci permette di fare altrimenti (ancora)
    //@ Se avessimo covarianza, potremmo mettere lo `zombieSpawner` di tipo `Spawner<Zombie>` dentro alla variabile di tipo `Spawner<Entity>`
    //@ Questo perche` `Zombie` is-a `Entity`
    //@ Potremmo chiamare `spawn` e salvare il risultato dentro a un `Entity`, anche se il tipo dinamico di quello che viene ritornato e` `Zombie`, va bene perche` il tipo statico della variabile e` un super-tipo di `Zombie`
    //@ Questo viene chiamato producer: il parametro di tipo e` nel tipo di ritorno
    //@ Per i producers, la covarianza va bene, non genera errori di tipo, infatti possiamo mettere il valore di ritorno dentro una variabile di tipo `Entity`, che e` un supertipo, e viene trattato come tale, non possiamo fare errori
    //@ Cosideriamo invece il caso duale:
    //QUIZ: Potremmo fare `g e n e r i c S p a w n e r . s e t E n t i t y ( n e w  Z o m b i e ());` ?
    //@ ---
    //@ Il metodo si aspetta una `Entity`, e passare `Zombie` va bene, grazie al polimorfismo di sottotipo
    //@ Pero` allo stesso modo possiamo anche passare un `Creeper`, che va sempre bene per il polimorfismo di sottotipo
    //@ Questo pero` e` errato, abbiamo messo un `Creeper` dove ci aspettiamo ci siano solo `Zombie`
    //@ Il metodo `setEntity` e` un consumer: consuma un valore di tipo `T` invece di produrlo

    //@ Ora cambiamo supposizione: supponiamo di avere controvarianza
    //@ Quindi `Spawner<Entity>` is-a `Spawner<Zombie>`
    //@ In questo caso, potremmo assegnare il `genericSpawner` a una variabile di tipo `Spawner<Zombie>`
    //@ Questo pero` permetterebbe di usare `getEntity` e ottenere uno `Zombie`, ma in realta` abbiamo un `Creeper` quindi abbiamo un errore a runtime
    //@ Quindi, usare un consumer con controvarianza da errori
    //@ D'altro canto, proviamo a usare un producer: potremmo usare il `setSpawner` tranquillamente, perche` possiamo mettere uno `Zombie` dentro una variabile che si aspetta una `Entity`
    private static void contravarianceExample(){
        System.out.println();
        System.out.println("Supponiamo di avere covarianza");
        Spawner<Zombie> zombieSpawner = new Spawner<>();
        zombieSpawner.setEntity(new Zombie());
//        Spawner<Entity> genericSpawner = zombieSpawner;
//        Entity z = genericSpawner.spawn();
//        genericSpawner.setEntity(new Zombie());
//        genericSpawner.setEntity(new Creeper());
        System.out.println();
        System.out.println("Supponiamo di avere controvarianza");
        Spawner<Entity> genericSpawner = new Spawner<>();
        genericSpawner.setEntity(new Creeper(5));
//        Spawner<Zombie> zombieSpawner = genericSpawner;
//        Zombie z = zombieSpawner.getEntity()
//        zombieSpawner.setEntity(new Zombie());
    }
    //@ Formalmente, potremmo permettere covarianza se le variabli di tipo appaiono solo in posizioni 'positive', cioe` un tipo di ritorno
    //@ Invece, potremmo permettere controvarianza se sono in posizioni 'negative' cioe` argomenti
    //@ Scala permette di annotare i parametri di tipo cosi` da permettere questa flessibilita`
    //@ In Java invece, possiamo usare le wildcards

    //QUIZ: Cosa ha Java?
    //@ ---

    //@ ## Wildcards
    //@ Per rendere `Spawner<Zombie>` compatibile con `Spawner<Entity>`, Java ha una notazione speciale: '?'
    //@ Il '?' ci dice che non sappiamo il tipo specifico, ma possiamo dare delle regole per dire cosa possa andare bene al posto di '?'
    //@ La wildcard '?' si usa in 2 modi:
    //@ 1. `? extends TYPE`
    //@ 2. `? super TYPE`

    //@ #### Wildcards e covarianza
    //@ `? extends TYPE` si legge: un tipo che sia almeno un `TYPE`
    //@ Consideriamo `Spawner<? extends TYPE>`
    //@ Questo si legge: "uno Spawner che contenga qualcosa che sia almeno una Entity"
    //@ Possiamo ottenerne il contenuto come un `Entity`, anche se c'e` dentro un sottotipo
    //@ Non possiamo settare il contenuto ad un sottotipo di `Entity`
    //@ Infatti, come vediamo in `covarianceExtendsExample`, il contenuto potrebbe venir settato ad un `Creeper`, mentre lo spawner era di `Zombie`
    //@ In questo caso, viene fatto enforcement di covarianza e viene impedito di creare un errore di tipo
    private static void covarianceExtendsExample() {
        Spawner<Zombie> zombieSpawner = new Spawner<>();
        zombieSpawner.setEntity(new Zombie());

        Spawner<? extends Entity> source = zombieSpawner;
        Entity e = source.spawn();
        System.out.println("Spawned: " + e);
        // source.setEntity(new Zombie()); // COMPILER ERROR!
    }

    //@ #### Wildcards e controvarianza
    //@ `? super TYPE` si legge: un tipo che sia un parent di `TYPE`
    //@ Consideriamo `Spawner<? super Zombie>`
    //@ Possiamo trattarlo come uno `Spawner` per metterci degli `Zombie`
    //@ Infatti il metodo `contravarianceSuperExample` mette uno `Zombie` in questo spawner
    //@ Pero` quando otteniamo il contenuto dello spawner, non sappiamo gran che, possiamo salvare questo contenuto solo in una variabile di tipo parent di `Zombie`
    //@ Inoltre dobbiamo fare un cast.
    private static void contravarianceSuperExample() {
        Spawner<Entity> generalSpawner = new Spawner<>();

        Spawner<? super Zombie> destination = generalSpawner;
        destination.setEntity(new Zombie());
        //QUIZ: Possiamo decommentare queste righe?
        //@ ---
//        Zombie z = destination.spawn();
//        Entity z = (Entity) destination.spawn();
    }

    //@ ## Acronimo: PECS
    //@ * P-roducer E-xtends: se l'oggetto produce un valore si usa `? extends`
    //@ * C-onsumer S-uper: se l'oggetto prende un valore si usa `? super`
    //@ Nel metodo `pecsExample` usiamo il metodo helper `transferMob` per spostare un mob da uno spawner di `Zombie` a uno spawner di `Entity`
    //@ Questo e` ok perche` il producer estende `Entity`, in quanto `Spawner<Entity>` mentre il consumer e` un super di `Entity`, in quanto e` uno `Spawner<Entity>`
    private static void pecsExample() {
        Spawner<Zombie> zSource = new Spawner<>();
        zSource.setEntity(new Zombie());

        Spawner<Entity> eDest = new Spawner<>();
        transferMob(zSource, eDest);
    }
    public static void transferMob(Spawner<? extends Entity> producer,
                                   Spawner<? super Entity> consumer) {
        Entity e = producer.spawn();
        consumer.setEntity(e);
        System.out.println("Transferred: " + e);
    }


    //@ ## Link Utili
    //@ - Slides con un buon recap e topics avanzati sulle Wildcards https://ethz.ch/content/dam/ethz/special-interest/infk/chair-program-method/pm/documents/Education/Courses/as2025/coop/lecture04-Types.pdf
    //@ -
    //@ FIXME: se avete suggerimenti, fateli via pull request

    //@ ## Follow-up
    //@ -
}



