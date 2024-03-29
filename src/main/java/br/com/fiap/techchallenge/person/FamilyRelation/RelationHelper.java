package br.com.fiap.techchallenge.person.FamilyRelation;

public class RelationHelper {

    public static String getRelationForAncestor(int depth) {
        if (depth == 1) return "PARENT";
        if (depth == 2) return "GRANDPARENT";

        return "GREAT-".repeat(depth-2) + "GRANDPARENT";
    }

    public static String getRelationForDescendant(int depth) {
        if (depth == 1) return "SON";
        if (depth == 2) return "GRANDSON";

        return "GREAT-".repeat(depth-2) + "GRANDSON";
    }

    public static String getRelationForBrother() {
        return "BROTHER";
    }
}
