# ComplianceRGPD_NDONGMO_CHACHA

Dans ce projet, nous avons faire deux micro-services:
Le permier est Service1Delete: ici un l'utilisateur demande à ce que ces données personnelles soit supprimée.
Pour ce fait, nous avons écrire un object Delete qui contient le chemin où se trouve le fichier csv contenant  les informations du client.
Nous avons implémenté un scénario test dans lequel un utilisateur nous donne son numéro identifiant et ainsi nous pouvons à partir de cette information supprimé la ligne correspondant à ce ID.
C'est à ce moment que nous faisons appel à l'object mainDelete qui exécute le souhait de l'utilisateur.


Le deuxième est Service2Hacher: Un utilisateur souhaiterais que ces données reste confidentielles.
Dans ce cas, nous avons écrire un object Hacher qui permet de mettre en place les pratique pour hacher les données de chaque utilisateur de notre fichier csv. 
Ensuite pour la tester, nous faisons essayons d'afficher les informations du fichier csv et nous remarquons que ces derniers ont été tous hacher dans un champs UUDI.
Enfin avec l'object mainHacher, nous executons le Hachage de chaque donnée.
