# Quick Repair - Team 6

Στα πλαίσια της εργασίας θα αναπτύξετε μια εφαρμογή η οποία θα διευκολύνει την εύρεση του κατάλληλου τεχνικού για επισκευή διαφόρων βλαβών στο σπίτι. Οι χρήστες θα είναι είτε τεχνικοί είτε πελάτες. Οι τεχνικοί θα κάνουν εγγραφή και θα εντάσσονται σε μια από τις διαθέσιμες κατηγορίες - ειδικότητες. Θα δηλώνουν τις περιοχές τις οποίες εξυπηρετούν, τις εργασίες τις οποίες προσφέρουν, τις μέρες και ώρες τις οποίες είναι διαθέσιμοι, τρόπους επικοινωνίας καθώς και τις τιμές. 

Οι πελάτες θα μπορούν να μπαίνουν στην εφαρμογή χωρίς εγγραφή και να βλέπουν τα στοιχεία των εγγεγραμμένων τεχνικών. Κάνοντας εγγραφή θα μπορούν να κλείσουν ραντεβού με τεχνικό της επιλογής τους. Η αναζήτηση των τεχνικών θα γίνεται με βάση την ειδικότητα, την περιοχή, τη διαθεσιμότητα και την τιμή ανά προσφερόμενη εργασία. Η εφαρμογή θα προβάλλει μόνο τις διαθέσιμες ώρες για τα ραντεβού. Όταν ο πελάτης κλείσει κάποιο ραντεβού η εφαρμογή θα ενημερώνει τον τεχνικό με τον τρόπο που αυτός έχει επιλέξει (e-mail, τηλ, SMS) και αυτός θα μπαίνει στην εφαρμογή για να επιβεβαιώσει το ραντεβού. Με την επιβεβαίωση η εφαρμογή θα στέλνει e-mail στον πελάτη για την επιβεβαίωση του ραντεβού και θα δεσμεύει το συγκεκριμένο ραντεβού για τον συγκεκριμένο τεχνικό ώστε να μην είναι πλέον διαθέσιμο.
Με την ολοκλήρωση της εργασίας η εφαρμογή θα επιτρέπει την εξόφληση και αξιολόγηση του τεχνικού.

## Διάγραμμα περιπτώσεων χρήσης

![Use case diagram](requirements/diagrams/use_case_R1.png)

## Σύντομη περιγραφή περιπτώσεων χρήσεων

### Διαχείριση χρήστη

Ένας χρήστης καταγράφει στο σύστημα τα στοιχεία του. Αν ο χρήστης είναι τεχνικός επιλέγει ειδικότητα , εργάσιμες μέρες και ώρες , περιοχές , της εργασιες που προσφέρει , τρόπους επικοινωνίας και τιμές. Αν ο χρήστης είναι πελάτης καταγράφει τα προσωπικά του στοιχεία.

### Αναζήτηση

Ένας πελάτης εισάγει τα κριτήρια αναζήτησης (ειδικότητα, περιοχή, διαθεσιμότητα, τιμή ανα προσφερόμενη εργασία) και του εμφανίζει μια λίστα με τα διαθέσιμα ραντεβού.

### Δημιουργία ραντεβού

Αφού ένας πελάτης επιλέξει την διαθέσιμη ώρα για ραντεβού και τον επιθυμητό τρόπο πληρωμής (μετρητά, κάρτα) , πληροφορείται ο τεχνικός (SMS/e-mail) και το αποδέχεται. Αφού το αποδεχθεί ενημερώνεται ο πελάτης για το συγκεκριμένο ραντεβού με τον συγκεκριμένο τεχνικό ώστε να μην είναι πλέον διαθέσιμο.

### Ολοκλήρωση εργασίας

Αφου επιβεβαιώσει ο τεχνικός πως ολοκληρώθηκε η εργασία, ειδοποιείται ο πελάτης και αυτός καταβάλλει το απαιτούμενο ποσό και καταθέτει την αξιολόγηση του στο σύστημα για τον συγκεκριμένο τεχνικό.