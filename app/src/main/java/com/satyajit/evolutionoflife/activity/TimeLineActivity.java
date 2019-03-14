package com.satyajit.evolutionoflife.activity;




import android.os.AsyncTask;

import de.blox.graphview.Graph;
import de.blox.graphview.GraphAdapter;
import de.blox.graphview.Node;
import de.blox.graphview.tree.BuchheimWalkerAlgorithm;
import de.blox.graphview.tree.BuchheimWalkerConfiguration;

/**
 * This is a heavy activity , lots of CPU resources are used for this activity
 * I have tried to do Optimisations to the top level
 * Might implement with RxJava in the next updates
 */


public class TimeLineActivity extends  GraphActivity {

    Graph graph;


    @Override
    public Graph createGraph() {
     graph = new Graph();


        new Loader().execute();

        return graph;
    }




    @Override
    public void setEvolution(GraphAdapter adapter) {

        new Loader2().execute();
    }


    public class Loader extends AsyncTask<String, Integer, Long> {



        @Override
        protected void onPreExecute() {

        }

        protected void onProgressUpdate(Integer... progress) {


        }

        protected void onPostExecute(Long Result) {

        }

        @Override
        protected Long doInBackground(String... strings) {

            final Node node1 = new Node(getNodeText());
            final Node node2 = new Node(getNodeText());
            final Node node3 = new Node(getNodeText());
            final Node node4 = new Node(getNodeText());
            final Node node5 = new Node(getNodeText());
            final Node node6 = new Node(getNodeText());
            final Node node8 = new Node(getNodeText());
            final Node node7 = new Node(getNodeText());
            final Node node9 = new Node(getNodeText());
            final Node node10 = new Node(getNodeText());
            final Node node11 = new Node(getNodeText());
            final Node node12 = new Node(getNodeText());
            final Node node13 = new Node(getNodeText());
            final Node node14 = new Node(getNodeText());
            final Node node15 = new Node(getNodeText());
            final Node node16 = new Node(getNodeText());
            final Node node17 = new Node(getNodeText());
            final Node node18 = new Node(getNodeText());
            final Node node19 = new Node(getNodeText());
            final Node node20 = new Node(getNodeText());
            final Node node21 = new Node(getNodeText());
            final Node node22 = new Node(getNodeText());
            final Node node23 = new Node(getNodeText());
            final Node node24 = new Node(getNodeText());
            final Node node25 = new Node(getNodeText());
            final Node node26 = new Node(getNodeText());
            final Node node27 = new Node(getNodeText());
            final Node node28 = new Node(getNodeText());
            final Node node29 = new Node(getNodeText());
            final Node node30 = new Node(getNodeText());
            final Node node31 = new Node(getNodeText());
            final Node node32 = new Node(getNodeText());
            final Node node33 = new Node(getNodeText());
            final Node node34 = new Node(getNodeText());
            final Node node35 = new Node(getNodeText());
            final Node node36 = new Node(getNodeText());
            final Node node37 = new Node(getNodeText());
            final Node node38 = new Node(getNodeText());
            final Node node39 = new Node(getNodeText());
            final Node node40 = new Node(getNodeText());
            final Node node41 = new Node(getNodeText());
            final Node node42 = new Node(getNodeText());
            final Node node43 = new Node(getNodeText());
            final Node node44 = new Node(getNodeText());
            final Node node45 = new Node(getNodeText());
            final Node node46 = new Node(getNodeText());
            final Node node47 = new Node(getNodeText());
            final Node node48 = new Node(getNodeText());
            final Node node49 = new Node(getNodeText());
            final Node node50 = new Node(getNodeText());
            final Node node51 = new Node(getNodeText());
            final Node node52 = new Node(getNodeText());
            final Node node53 = new Node(getNodeText());
            final Node node54 = new Node(getNodeText());
            final Node node55 = new Node(getNodeText());
            final Node node56 = new Node(getNodeText());
            final Node node57 = new Node(getNodeText());
            final Node node58 = new Node(getNodeText());
            final Node node59 = new Node(getNodeText());
            final Node node60 = new Node(getNodeText());
            final Node node61 = new Node(getNodeText());
            final Node node62 = new Node(getNodeText());
            final Node node63 = new Node(getNodeText());
            final Node node64 = new Node(getNodeText());
            final Node node65 = new Node(getNodeText());
            final Node node66 = new Node(getNodeText());
            final Node node67 = new Node(getNodeText());
            final Node node68 = new Node(getNodeText());
            final Node node69 = new Node(getNodeText());
            final Node node70 = new Node(getNodeText());
            final Node node71 = new Node(getNodeText());
            final Node node72 = new Node(getNodeText());
            final Node node73 = new Node(getNodeText());
            final Node node74 = new Node(getNodeText());
            final Node node75 = new Node(getNodeText());
            final Node node76 = new Node(getNodeText());
            final Node node77 = new Node(getNodeText());
            final Node node78 = new Node(getNodeText());
            final Node node79 = new Node(getNodeText());
            final Node node80 = new Node(getNodeText());
            final Node node81 = new Node(getNodeText());
            final Node node82 = new Node(getNodeText());
            final Node node83 = new Node(getNodeText());
            final Node node84 = new Node(getNodeText());
            final Node node85 = new Node(getNodeText());
            final Node node86 = new Node(getNodeText());
            final Node node87 = new Node(getNodeText());
            final Node node88 = new Node(getNodeText());
            final Node node89 = new Node(getNodeText());
            final Node node90 = new Node(getNodeText());
            final Node node91 = new Node(getNodeText());
            final Node node92 = new Node(getNodeText());
            final Node node93 = new Node(getNodeText());
            final Node node94 = new Node(getNodeText());
            final Node node95 = new Node(getNodeText());
            final Node node96 = new Node(getNodeText());
            final Node node97 = new Node(getNodeText());
            final Node node98 = new Node(getNodeText());
            final Node node99 = new Node(getNodeText());
            final Node node100 = new Node(getNodeText());
            final Node node101 = new Node(getNodeText());
            final Node node102 = new Node(getNodeText());
            final Node node103 = new Node(getNodeText());
            final Node node104 = new Node(getNodeText());
            final Node node105 = new Node(getNodeText());
            final Node node106 = new Node(getNodeText());
            final Node node107 = new Node(getNodeText());
            final Node node108 = new Node(getNodeText());
            final Node node109 = new Node(getNodeText());
            final Node node110 = new Node(getNodeText());
            final Node node111 = new Node(getNodeText());
            final Node node112 = new Node(getNodeText());
            final Node node113 = new Node(getNodeText());
            final Node node114 = new Node(getNodeText());
            final Node node115 = new Node(getNodeText());
            final Node node116 = new Node(getNodeText());
            final Node node117 = new Node(getNodeText());
            final Node node118 = new Node(getNodeText());
            final Node node119 = new Node(getNodeText());
            final Node node120 = new Node(getNodeText());
            final Node node121 = new Node(getNodeText());
            final Node node122 = new Node(getNodeText());
            final Node node123 = new Node(getNodeText());
            final Node node124 = new Node(getNodeText());
            final Node node125 = new Node(getNodeText());
            final Node node126 = new Node(getNodeText());
            final Node node127 = new Node(getNodeText());
            final Node node128 = new Node(getNodeText());
            final Node node129 = new Node(getNodeText());
            final Node node130 = new Node(getNodeText());
            final Node node131 = new Node(getNodeText());
            final Node node132 = new Node(getNodeText());
            final Node node133 = new Node(getNodeText());
            final Node node134 = new Node(getNodeText());
            final Node node135 = new Node(getNodeText());
            final Node node136 = new Node(getNodeText());
            final Node node137 = new Node(getNodeText());
            final Node node138 = new Node(getNodeText());
            final Node node139 = new Node(getNodeText());
            final Node node140 = new Node(getNodeText());
            final Node node141 = new Node(getNodeText());
            final Node node142 = new Node(getNodeText());
            final Node node143 = new Node(getNodeText());
            final Node node144 = new Node(getNodeText());
            final Node node145 = new Node(getNodeText());

            final Node node146 = new Node(getNodeText());
            final Node node147 = new Node(getNodeText());
            final Node node148 = new Node(getNodeText());
            final Node node149 = new Node(getNodeText());
            final Node node150 = new Node(getNodeText());
            final Node node151 = new Node(getNodeText());
            final Node node152 = new Node(getNodeText());
            final Node node153 = new Node(getNodeText());
            final Node node154 = new Node(getNodeText());
            final Node node155 = new Node(getNodeText());
            final Node node156 = new Node(getNodeText());
            final Node node157 = new Node(getNodeText());
            final Node node158 = new Node(getNodeText());
            final Node node159 = new Node(getNodeText());
            final Node node160 = new Node(getNodeText());
            final Node node161 = new Node(getNodeText());
            final Node node162 = new Node(getNodeText());
            final Node node163 = new Node(getNodeText());
            final Node node164 = new Node(getNodeText());
            final Node node165 = new Node(getNodeText());
            final Node node166 = new Node(getNodeText());
            final Node node167 = new Node(getNodeText());
            final Node node168 = new Node(getNodeText());
            final Node node169 = new Node(getNodeText());
            final Node node170 = new Node(getNodeText());
            final Node node171 = new Node(getNodeText());
            final Node node172 = new Node(getNodeText());
            final Node node173 = new Node(getNodeText());
            final Node node174 = new Node(getNodeText());
            final Node node175 = new Node(getNodeText());
            final Node node176 = new Node(getNodeText());
            final Node node177 = new Node(getNodeText());
            final Node node178 = new Node(getNodeText());
            final Node node179 = new Node(getNodeText());
            final Node node180 = new Node(getNodeText());
            final Node node181 = new Node(getNodeText());
            final Node node182 = new Node(getNodeText());
            final Node node183 = new Node(getNodeText());
            final Node node184 = new Node(getNodeText());
            final Node node185 = new Node(getNodeText());
            final Node node186 = new Node(getNodeText());
            final Node node187 = new Node(getNodeText());
            final Node node188 = new Node(getNodeText());
            final Node node189 = new Node(getNodeText());
            final Node node190 = new Node(getNodeText());
            final Node node191 = new Node(getNodeText());
            final Node node192 = new Node(getNodeText());
            final Node node193 = new Node(getNodeText());
            final Node node194 = new Node(getNodeText());
            final Node node195 = new Node(getNodeText());
            final Node node196 = new Node(getNodeText());
            final Node node197 = new Node(getNodeText());
            final Node node198 = new Node(getNodeText());
            final Node node199 = new Node(getNodeText());
            final Node node200 = new Node(getNodeText());
            final Node node201 = new Node(getNodeText());




            graph.addEdge(node1, node2);
            graph.addEdge(node1, node3);
            graph.addEdge(node1, node4);


            //ARch dm
            graph.addEdge(node4, node5);
            graph.addEdge(node4, node6);
            graph.addEdge(node4, node7);


            //Euri Phy
            graph.addEdge(node7, node8);
            graph.addEdge(node7, node9);


            //Crena
            graph.addEdge(node5, node10);


            //Eukaryote Dm
            graph.addEdge(node3, node11);
            graph.addEdge(node3, node12);
            graph.addEdge(node3, node13);
            graph.addEdge(node3, node14);
            graph.addEdge(node3, node15);
            graph.addEdge(node3, node16);
            graph.addEdge(node3, node17);
            graph.addEdge(node3, node18);
            graph.addEdge(node3, node19);


            //Fungus King
            graph.addEdge(node15, node20);
            graph.addEdge(node15, node21);
            graph.addEdge(node15, node22);

            //Plant King
            graph.addEdge(node18, node23);
            graph.addEdge(node18, node24);
            graph.addEdge(node18, node25);


            //Land Plants
            graph.addEdge(node24, node26);

            //Vascular Plants
            graph.addEdge(node26, node27);


            //seed plants
            graph.addEdge(node27, node28);

            //conifer as node
            graph.addEdge(node28, node29);

            //order pinales
            graph.addEdge(node29, node30);
            graph.addEdge(node29, node31);

            //Family Pin
            graph.addEdge(node30, node32);
            graph.addEdge(node30, node33);


            //seed plants
            graph.addEdge(node27, node34);

            //AgeioSperm
            graph.addEdge(node34, node35);

            //Monocots
            graph.addEdge(node35, node36);
            graph.addEdge(node35, node37);
            graph.addEdge(node35, node38);
            graph.addEdge(node35, node39);


            //AgioSperm
            graph.addEdge(node34, node40);

            //Dicots
            graph.addEdge(node40, node41);
            graph.addEdge(node40, node42);


            //Eudiocots
            graph.addEdge(node42, node43);
            graph.addEdge(node42, node44);
            graph.addEdge(node42, node45);

            //Animal King
            graph.addEdge(node12, node46);

            //Bilateria
            graph.addEdge(node46, node47);
            graph.addEdge(node46, node48);

            //Deutero
            graph.addEdge(node48, node49);
            graph.addEdge(node48, node50);


            //Protostomes
            graph.addEdge(node47, node51);
            graph.addEdge(node47, node52);
            graph.addEdge(node47, node53);
            graph.addEdge(node47, node54);

            //Phylum Malusca
            graph.addEdge(node54, node55);
            graph.addEdge(node54, node56);

            //Anthropoda
            graph.addEdge(node53, node57);

            //Insect Class
            graph.addEdge(node57, node58);
            graph.addEdge(node57, node59);
            graph.addEdge(node57, node60);
            graph.addEdge(node57, node61);

            //Order Hype
            graph.addEdge(node61, node62);


            //phylum chordata
            graph.addEdge(node50, node63);
            graph.addEdge(node50, node64);

            //Vertebrates
            graph.addEdge(node64, node65);
            graph.addEdge(node64, node66);


            //Jawed Fish
            graph.addEdge(node66, node67);
            graph.addEdge(node66, node68);

            //Bony Fish
            graph.addEdge(node68, node69);
            graph.addEdge(node68, node73);

            //Actino
            graph.addEdge(node69, node70);
            graph.addEdge(node69, node71);
            graph.addEdge(node69, node72);

            //Sarcopter
            graph.addEdge(node73, node74);
            graph.addEdge(node73, node75);


            //TetraPods
            graph.addEdge(node74, node76);


            //Ambhibian
            graph.addEdge(node76, node77);
            graph.addEdge(node76, node78);


            graph.addEdge(node74, node79);

            graph.addEdge(node79, node80);
            graph.addEdge(node79, node81);


            //Synapsids
            graph.addEdge(node80, node82);
            graph.addEdge(node80, node83);

            graph.addEdge(node83, node84);


            graph.addEdge(node81, node85);

            graph.addEdge(node85, node86);
            graph.addEdge(node85, node87);
            graph.addEdge(node85, node88);
            graph.addEdge(node85, node89);
            graph.addEdge(node85, node90);
            graph.addEdge(node85, node91);

            graph.addEdge(node87, node92);
            graph.addEdge(node87, node93);
            graph.addEdge(node87, node94);


            graph.addEdge(node90, node95);
            graph.addEdge(node90, node96);

            graph.addEdge(node95, node97);

            graph.addEdge(node97, node98);
            graph.addEdge(node97, node99);


            graph.addEdge(node95, node100);


            graph.addEdge(node100, node101);
            graph.addEdge(node100, node102);
            graph.addEdge(node100, node103);


            graph.addEdge(node96, node104);
            graph.addEdge(node96, node105);



            graph.addEdge(node101, node106);
            graph.addEdge(node101, node107);
            graph.addEdge(node101, node108);
            graph.addEdge(node101, node109);


            graph.addEdge(node108, node110);
            graph.addEdge(node108, node111);
            graph.addEdge(node108, node112);


            graph.addEdge(node84, node113);

            graph.addEdge(node113, node114);
            graph.addEdge(node113, node115);

            graph.addEdge(node115, node116);

            graph.addEdge(node116, node117);
            graph.addEdge(node116, node118);


            graph.addEdge(node118, node119);
            graph.addEdge(node118, node120);
            graph.addEdge(node118, node121);
            graph.addEdge(node118, node122);

            graph.addEdge(node115, node123);

            graph.addEdge(node123, node124);


            graph.addEdge(node124, node125);
            graph.addEdge(node124, node126);


            graph.addEdge(node125, node127);
            graph.addEdge(node125, node128);


            graph.addEdge(node126, node129);

            graph.addEdge(node129, node130);


            graph.addEdge(node130, node131);
            graph.addEdge(node130, node132);
            graph.addEdge(node130, node133);

            graph.addEdge(node123, node134);

            graph.addEdge(node134, node135);
            graph.addEdge(node134, node136);


            graph.addEdge(node134, node137);

            graph.addEdge(node137, node138);
            graph.addEdge(node137, node139);
            graph.addEdge(node137, node140);
            graph.addEdge(node137, node141);
            graph.addEdge(node137, node142);
            graph.addEdge(node137, node143);

            graph.addEdge(node143, node144);
            graph.addEdge(node143, node145);

            graph.addEdge(node140, node146);
            graph.addEdge(node140, node147);

            graph.addEdge(node147, node148);
            graph.addEdge(node147, node149);

            graph.addEdge(node148, node150);



            graph.addEdge(node142, node151);
            graph.addEdge(node142, node152);
            graph.addEdge(node142, node153);


            graph.addEdge(node152, node154);
            graph.addEdge(node152, node155);

            graph.addEdge(node153, node156);

            graph.addEdge(node134, node157);

            graph.addEdge(node157, node158);
            graph.addEdge(node157, node159);


            graph.addEdge(node158, node160);
            graph.addEdge(node158, node161);

            graph.addEdge(node159, node162);

            graph.addEdge(node162, node163);
            graph.addEdge(node162, node164);
            graph.addEdge(node162, node165);


            graph.addEdge(node159, node166);
            graph.addEdge(node159, node167);
            graph.addEdge(node159, node168);
            graph.addEdge(node159, node169);


            graph.addEdge(node167, node170);
            graph.addEdge(node167, node171);

            graph.addEdge(node168, node172);

            graph.addEdge(node123, node173);

            graph.addEdge(node173, node174);

            graph.addEdge(node174, node175);
            graph.addEdge(node174, node176);
            graph.addEdge(node174, node177);
            graph.addEdge(node174, node178);

            graph.addEdge(node178, node179);
            graph.addEdge(node178, node180);


            graph.addEdge(node180, node181);
            graph.addEdge(node180, node182);
            graph.addEdge(node180, node183);

            graph.addEdge(node183, node184);
            graph.addEdge(node183, node185);

            graph.addEdge(node185, node186);
            graph.addEdge(node185, node187);
            graph.addEdge(node185, node188);


            graph.addEdge(node188, node189);
            graph.addEdge(node188, node190);
            graph.addEdge(node188, node191);


            graph.addEdge(node189, node192);
            graph.addEdge(node189, node193);

            graph.addEdge(node193, node194);
            graph.addEdge(node193, node195);
            graph.addEdge(node193, node196);


            graph.addEdge(node195, node197);
            graph.addEdge(node195, node198);

            graph.addEdge(node198, node199);
            graph.addEdge(node198, node200);
            graph.addEdge(node198, node201);

            return null;
        }
    }

    public class Loader2 extends AsyncTask<String, Integer, Long> {



        @Override
        protected void onPreExecute() {

        }

        protected void onProgressUpdate(Integer... progress) {


        }

        protected void onPostExecute(Long Result) {

        }

        @Override
        protected Long doInBackground(String... strings) {

            final BuchheimWalkerConfiguration configuration = new BuchheimWalkerConfiguration.Builder()
                    .setSiblingSeparation(100)
                    .setLevelSeparation(300)
                    .setSubtreeSeparation(300)
                    .setOrientation(BuchheimWalkerConfiguration.ORIENTATION_TOP_BOTTOM)
                    .build();
            adapter.setAlgorithm(new BuchheimWalkerAlgorithm(configuration));


            return null;
        }
    }


}
