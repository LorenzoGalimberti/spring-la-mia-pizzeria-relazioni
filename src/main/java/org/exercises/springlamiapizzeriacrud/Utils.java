package org.exercises.springlamiapizzeriacrud;

public interface Utils {
    // metodo statico
    public static String getSlug(String name){
        // inserisco il codice
        // Rimuovi spazi all'inizio e alla fine della stringa
        String slug = name.trim();

        // Sostituisci spazi con '-'
        slug = slug.replaceAll("\\s+", "-");

        // Converti in minuscolo
        slug = slug.toLowerCase();

        return slug;

    }
}
