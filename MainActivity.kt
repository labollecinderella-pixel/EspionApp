package com.example.espionapp

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun MainScreen() {
    var showPseudoDialog by remember { mutableStateOf(false) }
    var generatedPseudo by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // En-tête avec bouton MON PROFIL et drapeau
        HeaderSection()

        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

        // Contenu principal avec colonne gauche et zones fonctionnelles
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            // Colonne verticale gauche
            LeftColumn()

            // Zone centrale avec les 4 sections
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                // Zone MISSIONS
                MissionsSection()

                Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

                // Zone CANAL
                CanalSection()

                Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

                // Zone RÉSEAU (Carte du monde)
                ReseauSection()

                Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

                // Zone PISTER
                PisterSection(
                    onSearchClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://trends.google.com/trends/"))
                        context.startActivity(intent)
                    },
                    onPseudosClick = {
                        generatedPseudo = generateUniquePseudo()
                        showPseudoDialog = true
                    }
                )
            }
        }
    }

    // Dialog pour afficher le pseudo
    if (showPseudoDialog) {
        PseudoDialog(
            pseudo = generatedPseudo,
            onDismiss = { showPseudoDialog = false }
        )
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { /* Action profil */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2C2C2C)
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            Text(
                text = "MON PROFIL",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "🇫🇷",
            fontSize = 32.sp,
            modifier = Modifier
                .clickable { /* Changement de langue */ }
                .padding(end = 20.dp)
        )
    }
}

@Composable
fun LeftColumn() {
    Column(
        modifier = Modifier
            .width(38.dp)
            .fillMaxHeight()
            .background(Color(0xFFF8F9FA)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Section MISSIONS
        VerticalTextSection(
            text = "MISSIONS",
            modifier = Modifier.height(196.dp)
        )

        // Section CANAL
        VerticalTextSection(
            text = "CANAL",
            modifier = Modifier.height(249.dp)
        )

        // Section RÉSEAU
        VerticalTextSection(
            text = "RÉSEAU",
            modifier = Modifier.height(249.dp)
        )

        // Section PISTER
        VerticalTextSection(
            text = "PISTER",
            modifier = Modifier.height(106.dp)
        )
    }
}

@Composable
fun VerticalTextSection(text: String, modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable { }
            .background(Color(0xFFF8F9FA)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            text.forEach { char ->
                Text(
                    text = char.toString(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF1A1A1A),
                    letterSpacing = 2.sp
                )
            }
        }
    }
}

@Composable
fun MissionsSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(196.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "(Zone de missions)",
            fontSize = 18.sp,
            color = Color(0xFF5F6368),
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun CanalSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(249.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "♂",
            fontSize = 80.sp,
            color = Color(0xFF4A90E2),
            modifier = Modifier.clickable { }
        )

        Spacer(modifier = Modifier.width(30.dp))

        Text(
            text = "VS",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2C2C2C)
        )

        Spacer(modifier = Modifier.width(30.dp))

        Text(
            text = "♀",
            fontSize = 80.sp,
            color = Color(0xFFFF69B4),
            modifier = Modifier.clickable { }
        )
    }
}

@Composable
fun ReseauSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(249.dp)
            .background(Color.White)
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        // IMPORTANT : Utilise l'image de la carte du monde
        Image(
            painter = painterResource(id = R.drawable.world_map_detailed),
            contentDescription = "Carte du monde",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun PisterSection(onSearchClick: () -> Unit, onPseudosClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(106.dp)
            .background(Color.White)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Bouton RECHERCHE
        ActionButton(
            text = "RECHERCHE",
            icon = "📊",
            onClick = onSearchClick
        )

        Spacer(modifier = Modifier.width(15.dp))

        // Bouton PSEUDOS
        ActionButton(
            text = "PSEUDOS",
            icon = "🔒",
            onClick = onPseudosClick
        )
    }
}

@Composable
fun ActionButton(text: String, icon: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFF8F9FA)
        ),
        shape = RoundedCornerShape(10.dp),
        border = androidx.compose.foundation.BorderStroke(2.dp, Color(0xFFE0E0E0)),
        modifier = Modifier
            .width(189.dp)
            .height(57.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = icon,
                fontSize = 20.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = text,
                fontSize = 13.sp,
                fontWeight = FontWeight.Black,
                color = Color(0xFF2C2C2C),
                letterSpacing = 1.sp
            )
        }
    }
}

@Composable
fun PseudoDialog(pseudo: String, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(15.dp),
            color = Color.White,
            modifier = Modifier.padding(20.dp)
        ) {
            Column(
                modifier = Modifier.padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "VOTRE PSEUDO UNIQUE",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF2C2C2C),
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFFF8F9FA),
                    modifier = Modifier.padding(bottom = 20.dp)
                ) {
                    Text(
                        text = pseudo,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4A90E2),
                        textAlign = TextAlign.Center,
                        letterSpacing = 2.sp,
                        modifier = Modifier.padding(20.dp)
                    )
                }

                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2C2C2C)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "FERMER",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

// Fonction pour générer un pseudo unique
fun generateUniquePseudo(): String {
    val alphabets = mapOf(
        "french" to "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ",
        "arabic" to "ابتثجحخدذرزسشصضطظعغفقكلمنهوي",
        "russian" to "абвгдежзийклмнопрстуфхцчшщъыьэюя",
        "chinese" to "的一是不了人我在有他这为之大来以个中上们到说国和地也子时道出而要于就下得可你年生自会那后能对着事其里所去行过家十用发天如然作方成者多日都三小军二无同么经法当起与好看学进种将还分此心前面又定见只主没公从",
        "hindi" to "अआइईउऊएऐओऔकखगघङचछजझञटठडढणतथदधनपफबभमयरलवशषसह"
    )

    val alphabetKeys = alphabets.keys.toList()
    val length = (8..10).random()
    val pseudo = StringBuilder()
    var lastAlphabet: String? = null

    repeat(length) {
        val availableAlphabets = alphabetKeys.filter { it != lastAlphabet }
        val chosenAlphabet = availableAlphabets.random()
        val letters = alphabets[chosenAlphabet]!!
        val randomLetter = letters.random()

        pseudo.append(randomLetter)
        lastAlphabet = chosenAlphabet
    }

    return pseudo.toString()
}package com.example.espionapp

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun MainScreen() {
    var showPseudoDialog by remember { mutableStateOf(false) }
    var generatedPseudo by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // En-tête avec bouton MON PROFIL et drapeau
        HeaderSection()

        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

        // Contenu principal avec colonne gauche et zones fonctionnelles
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            // Colonne verticale gauche
            LeftColumn()

            // Zone centrale avec les 4 sections
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                // Zone MISSIONS
                MissionsSection()

                Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

                // Zone CANAL
                CanalSection()

                Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

                // Zone RÉSEAU (Carte du monde)
                ReseauSection()

                Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

                // Zone PISTER
                PisterSection(
                    onSearchClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://trends.google.com/trends/"))
                        context.startActivity(intent)
                    },
                    onPseudosClick = {
                        generatedPseudo = generateUniquePseudo()
                        showPseudoDialog = true
                    }
                )
            }
        }
    }

    // Dialog pour afficher le pseudo
    if (showPseudoDialog) {
        PseudoDialog(
            pseudo = generatedPseudo,
            onDismiss = { showPseudoDialog = false }
        )
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { /* Action profil */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2C2C2C)
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            Text(
                text = "MON PROFIL",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "🇫🇷",
            fontSize = 32.sp,
            modifier = Modifier
                .clickable { /* Changement de langue */ }
                .padding(end = 20.dp)
        )
    }
}

@Composable
fun LeftColumn() {
    Column(
        modifier = Modifier
            .width(38.dp)
            .fillMaxHeight()
            .background(Color(0xFFF8F9FA)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Section MISSIONS
        VerticalTextSection(
            text = "MISSIONS",
            modifier = Modifier.height(196.dp)
        )

        // Section CANAL
        VerticalTextSection(
            text = "CANAL",
            modifier = Modifier.height(249.dp)
        )

        // Section RÉSEAU
        VerticalTextSection(
            text = "RÉSEAU",
            modifier = Modifier.height(249.dp)
        )

        // Section PISTER
        VerticalTextSection(
            text = "PISTER",
            modifier = Modifier.height(106.dp)
        )
    }
}

@Composable
fun VerticalTextSection(text: String, modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable { }
            .background(Color(0xFFF8F9FA)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            text.forEach { char ->
                Text(
                    text = char.toString(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF1A1A1A),
                    letterSpacing = 2.sp
                )
            }
        }
    }
}

@Composable
fun MissionsSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(196.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "(Zone de missions)",
            fontSize = 18.sp,
            color = Color(0xFF5F6368),
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun CanalSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(249.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "♂",
            fontSize = 80.sp,
            color = Color(0xFF4A90E2),
            modifier = Modifier.clickable { }
        )

        Spacer(modifier = Modifier.width(30.dp))

        Text(
            text = "VS",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2C2C2C)
        )

        Spacer(modifier = Modifier.width(30.dp))

        Text(
            text = "♀",
            fontSize = 80.sp,
            color = Color(0xFFFF69B4),
            modifier = Modifier.clickable { }
        )
    }
}

@Composable
fun ReseauSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(249.dp)
            .background(Color.White)
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        // IMPORTANT : Utilise l'image de la carte du monde
        Image(
            painter = painterResource(id = R.drawable.world_map_detailed),
            contentDescription = "Carte du monde",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun PisterSection(onSearchClick: () -> Unit, onPseudosClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(106.dp)
            .background(Color.White)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Bouton RECHERCHE
        ActionButton(
            text = "RECHERCHE",
            icon = "📊",
            onClick = onSearchClick
        )

        Spacer(modifier = Modifier.width(15.dp))

        // Bouton PSEUDOS
        ActionButton(
            text = "PSEUDOS",
            icon = "🔒",
            onClick = onPseudosClick
        )
    }
}

@Composable
fun ActionButton(text: String, icon: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFF8F9FA)
        ),
        shape = RoundedCornerShape(10.dp),
        border = androidx.compose.foundation.BorderStroke(2.dp, Color(0xFFE0E0E0)),
        modifier = Modifier
            .width(189.dp)
            .height(57.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = icon,
                fontSize = 20.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = text,
                fontSize = 13.sp,
                fontWeight = FontWeight.Black,
                color = Color(0xFF2C2C2C),
                letterSpacing = 1.sp
            )
        }
    }
}

@Composable
fun PseudoDialog(pseudo: String, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(15.dp),
            color = Color.White,
            modifier = Modifier.padding(20.dp)
        ) {
            Column(
                modifier = Modifier.padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "VOTRE PSEUDO UNIQUE",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF2C2C2C),
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFFF8F9FA),
                    modifier = Modifier.padding(bottom = 20.dp)
                ) {
                    Text(
                        text = pseudo,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4A90E2),
                        textAlign = TextAlign.Center,
                        letterSpacing = 2.sp,
                        modifier = Modifier.padding(20.dp)
                    )
                }

                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2C2C2C)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "FERMER",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

// Fonction pour générer un pseudo unique
fun generateUniquePseudo(): String {
    val alphabets = mapOf(
        "french" to "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ",
        "arabic" to "ابتثجحخدذرزسشصضطظعغفقكلمنهوي",
        "russian" to "абвгдежзийклмнопрстуфхцчшщъыьэюя",
        "chinese" to "的一是不了人我在有他这为之大来以个中上们到说国和地也子时道出而要于就下得可你年生自会那后能对着事其里所去行过家十用发天如然作方成者多日都三小军二无同么经法当起与好看学进种将还分此心前面又定见只主没公从",
        "hindi" to "अआइईउऊएऐओऔकखगघङचछजझञटठडढणतथदधनपफबभमयरलवशषसह"
    )

    val alphabetKeys = alphabets.keys.toList()
    val length = (8..10).random()
    val pseudo = StringBuilder()
    var lastAlphabet: String? = null

    repeat(length) {
        val availableAlphabets = alphabetKeys.filter { it != lastAlphabet }
        val chosenAlphabet = availableAlphabets.random()
        val letters = alphabets[chosenAlphabet]!!
        val randomLetter = letters.random()

        pseudo.append(randomLetter)
        lastAlphabet = chosenAlphabet
    }

    return pseudo.toString()
}package com.example.espionapp

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun MainScreen() {
    var showPseudoDialog by remember { mutableStateOf(false) }
    var generatedPseudo by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // En-tête avec bouton MON PROFIL et drapeau
        HeaderSection()

        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

        // Contenu principal avec colonne gauche et zones fonctionnelles
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            // Colonne verticale gauche
            LeftColumn()

            // Zone centrale avec les 4 sections
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                // Zone MISSIONS
                MissionsSection()

                Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

                // Zone CANAL
                CanalSection()

                Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

                // Zone RÉSEAU (Carte du monde)
                ReseauSection()

                Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

                // Zone PISTER
                PisterSection(
                    onSearchClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://trends.google.com/trends/"))
                        context.startActivity(intent)
                    },
                    onPseudosClick = {
                        generatedPseudo = generateUniquePseudo()
                        showPseudoDialog = true
                    }
                )
            }
        }
    }

    // Dialog pour afficher le pseudo
    if (showPseudoDialog) {
        PseudoDialog(
            pseudo = generatedPseudo,
            onDismiss = { showPseudoDialog = false }
        )
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { /* Action profil */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2C2C2C)
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            Text(
                text = "MON PROFIL",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "🇫🇷",
            fontSize = 32.sp,
            modifier = Modifier
                .clickable { /* Changement de langue */ }
                .padding(end = 20.dp)
        )
    }
}

@Composable
fun LeftColumn() {
    Column(
        modifier = Modifier
            .width(38.dp)
            .fillMaxHeight()
            .background(Color(0xFFF8F9FA)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Section MISSIONS
        VerticalTextSection(
            text = "MISSIONS",
            modifier = Modifier.height(196.dp)
        )

        // Section CANAL
        VerticalTextSection(
            text = "CANAL",
            modifier = Modifier.height(249.dp)
        )

        // Section RÉSEAU
        VerticalTextSection(
            text = "RÉSEAU",
            modifier = Modifier.height(249.dp)
        )

        // Section PISTER
        VerticalTextSection(
            text = "PISTER",
            modifier = Modifier.height(106.dp)
        )
    }
}

@Composable
fun VerticalTextSection(text: String, modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable { }
            .background(Color(0xFFF8F9FA)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            text.forEach { char ->
                Text(
                    text = char.toString(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF1A1A1A),
                    letterSpacing = 2.sp
                )
            }
        }
    }
}

@Composable
fun MissionsSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(196.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "(Zone de missions)",
            fontSize = 18.sp,
            color = Color(0xFF5F6368),
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun CanalSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(249.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "♂",
            fontSize = 80.sp,
            color = Color(0xFF4A90E2),
            modifier = Modifier.clickable { }
        )

        Spacer(modifier = Modifier.width(30.dp))

        Text(
            text = "VS",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2C2C2C)
        )

        Spacer(modifier = Modifier.width(30.dp))

        Text(
            text = "♀",
            fontSize = 80.sp,
            color = Color(0xFFFF69B4),
            modifier = Modifier.clickable { }
        )
    }
}

@Composable
fun ReseauSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(249.dp)
            .background(Color.White)
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        // IMPORTANT : Utilise l'image de la carte du monde
        Image(
            painter = painterResource(id = R.drawable.world_map_detailed),
            contentDescription = "Carte du monde",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun PisterSection(onSearchClick: () -> Unit, onPseudosClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(106.dp)
            .background(Color.White)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Bouton RECHERCHE
        ActionButton(
            text = "RECHERCHE",
            icon = "📊",
            onClick = onSearchClick
        )

        Spacer(modifier = Modifier.width(15.dp))

        // Bouton PSEUDOS
        ActionButton(
            text = "PSEUDOS",
            icon = "🔒",
            onClick = onPseudosClick
        )
    }
}

@Composable
fun ActionButton(text: String, icon: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFF8F9FA)
        ),
        shape = RoundedCornerShape(10.dp),
        border = androidx.compose.foundation.BorderStroke(2.dp, Color(0xFFE0E0E0)),
        modifier = Modifier
            .width(189.dp)
            .height(57.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = icon,
                fontSize = 20.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = text,
                fontSize = 13.sp,
                fontWeight = FontWeight.Black,
                color = Color(0xFF2C2C2C),
                letterSpacing = 1.sp
            )
        }
    }
}

@Composable
fun PseudoDialog(pseudo: String, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(15.dp),
            color = Color.White,
            modifier = Modifier.padding(20.dp)
        ) {
            Column(
                modifier = Modifier.padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "VOTRE PSEUDO UNIQUE",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF2C2C2C),
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFFF8F9FA),
                    modifier = Modifier.padding(bottom = 20.dp)
                ) {
                    Text(
                        text = pseudo,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4A90E2),
                        textAlign = TextAlign.Center,
                        letterSpacing = 2.sp,
                        modifier = Modifier.padding(20.dp)
                    )
                }

                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2C2C2C)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "FERMER",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

// Fonction pour générer un pseudo unique
fun generateUniquePseudo(): String {
    val alphabets = mapOf(
        "french" to "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ",
        "arabic" to "ابتثجحخدذرزسشصضطظعغفقكلمنهوي",
        "russian" to "абвгдежзийклмнопрстуфхцчшщъыьэюя",
        "chinese" to "的一是不了人我在有他这为之大来以个中上们到说国和地也子时道出而要于就下得可你年生自会那后能对着事其里所去行过家十用发天如然作方成者多日都三小军二无同么经法当起与好看学进种将还分此心前面又定见只主没公从",
        "hindi" to "अआइईउऊएऐओऔकखगघङचछजझञटठडढणतथदधनपफबभमयरलवशषसह"
    )

    val alphabetKeys = alphabets.keys.toList()
    val length = (8..10).random()
    val pseudo = StringBuilder()
    var lastAlphabet: String? = null

    repeat(length) {
        val availableAlphabets = alphabetKeys.filter { it != lastAlphabet }
        val chosenAlphabet = availableAlphabets.random()
        val letters = alphabets[chosenAlphabet]!!
        val randomLetter = letters.random()

        pseudo.append(randomLetter)
        lastAlphabet = chosenAlphabet
    }

    return pseudo.toString()
}package com.example.espionapp

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun MainScreen() {
    var showPseudoDialog by remember { mutableStateOf(false) }
    var generatedPseudo by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // En-tête avec bouton MON PROFIL et drapeau
        HeaderSection()

        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

        // Contenu principal avec colonne gauche et zones fonctionnelles
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            // Colonne verticale gauche
            LeftColumn()

            // Zone centrale avec les 4 sections
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                // Zone MISSIONS
                MissionsSection()

                Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

                // Zone CANAL
                CanalSection()

                Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

                // Zone RÉSEAU (Carte du monde)
                ReseauSection()

                Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

                // Zone PISTER
                PisterSection(
                    onSearchClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://trends.google.com/trends/"))
                        context.startActivity(intent)
                    },
                    onPseudosClick = {
                        generatedPseudo = generateUniquePseudo()
                        showPseudoDialog = true
                    }
                )
            }
        }
    }

    // Dialog pour afficher le pseudo
    if (showPseudoDialog) {
        PseudoDialog(
            pseudo = generatedPseudo,
            onDismiss = { showPseudoDialog = false }
        )
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { /* Action profil */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2C2C2C)
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            Text(
                text = "MON PROFIL",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "🇫🇷",
            fontSize = 32.sp,
            modifier = Modifier
                .clickable { /* Changement de langue */ }
                .padding(end = 20.dp)
        )
    }
}

@Composable
fun LeftColumn() {
    Column(
        modifier = Modifier
            .width(38.dp)
            .fillMaxHeight()
            .background(Color(0xFFF8F9FA)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Section MISSIONS
        VerticalTextSection(
            text = "MISSIONS",
            modifier = Modifier.height(196.dp)
        )

        // Section CANAL
        VerticalTextSection(
            text = "CANAL",
            modifier = Modifier.height(249.dp)
        )

        // Section RÉSEAU
        VerticalTextSection(
            text = "RÉSEAU",
            modifier = Modifier.height(249.dp)
        )

        // Section PISTER
        VerticalTextSection(
            text = "PISTER",
            modifier = Modifier.height(106.dp)
        )
    }
}

@Composable
fun VerticalTextSection(text: String, modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable { }
            .background(Color(0xFFF8F9FA)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            text.forEach { char ->
                Text(
                    text = char.toString(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF1A1A1A),
                    letterSpacing = 2.sp
                )
            }
        }
    }
}

@Composable
fun MissionsSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(196.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "(Zone de missions)",
            fontSize = 18.sp,
            color = Color(0xFF5F6368),
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun CanalSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(249.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "♂",
            fontSize = 80.sp,
            color = Color(0xFF4A90E2),
            modifier = Modifier.clickable { }
        )

        Spacer(modifier = Modifier.width(30.dp))

        Text(
            text = "VS",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2C2C2C)
        )

        Spacer(modifier = Modifier.width(30.dp))

        Text(
            text = "♀",
            fontSize = 80.sp,
            color = Color(0xFFFF69B4),
            modifier = Modifier.clickable { }
        )
    }
}

@Composable
fun ReseauSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(249.dp)
            .background(Color.White)
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        // IMPORTANT : Utilise l'image de la carte du monde
        Image(
            painter = painterResource(id = R.drawable.world_map_detailed),
            contentDescription = "Carte du monde",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun PisterSection(onSearchClick: () -> Unit, onPseudosClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(106.dp)
            .background(Color.White)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Bouton RECHERCHE
        ActionButton(
            text = "RECHERCHE",
            icon = "📊",
            onClick = onSearchClick
        )

        Spacer(modifier = Modifier.width(15.dp))

        // Bouton PSEUDOS
        ActionButton(
            text = "PSEUDOS",
            icon = "🔒",
            onClick = onPseudosClick
        )
    }
}

@Composable
fun ActionButton(text: String, icon: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFF8F9FA)
        ),
        shape = RoundedCornerShape(10.dp),
        border = androidx.compose.foundation.BorderStroke(2.dp, Color(0xFFE0E0E0)),
        modifier = Modifier
            .width(189.dp)
            .height(57.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = icon,
                fontSize = 20.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = text,
                fontSize = 13.sp,
                fontWeight = FontWeight.Black,
                color = Color(0xFF2C2C2C),
                letterSpacing = 1.sp
            )
        }
    }
}

@Composable
fun PseudoDialog(pseudo: String, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(15.dp),
            color = Color.White,
            modifier = Modifier.padding(20.dp)
        ) {
            Column(
                modifier = Modifier.padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "VOTRE PSEUDO UNIQUE",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF2C2C2C),
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFFF8F9FA),
                    modifier = Modifier.padding(bottom = 20.dp)
                ) {
                    Text(
                        text = pseudo,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4A90E2),
                        textAlign = TextAlign.Center,
                        letterSpacing = 2.sp,
                        modifier = Modifier.padding(20.dp)
                    )
                }

                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2C2C2C)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "FERMER",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

// Fonction pour générer un pseudo unique
fun generateUniquePseudo(): String {
    val alphabets = mapOf(
        "french" to "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ",
        "arabic" to "ابتثجحخدذرزسشصضطظعغفقكلمنهوي",
        "russian" to "абвгдежзийклмнопрстуфхцчшщъыьэюя",
        "chinese" to "的一是不了人我在有他这为之大来以个中上们到说国和地也子时道出而要于就下得可你年生自会那后能对着事其里所去行过家十用发天如然作方成者多日都三小军二无同么经法当起与好看学进种将还分此心前面又定见只主没公从",
        "hindi" to "अआइईउऊएऐओऔकखगघङचछजझञटठडढणतथदधनपफबभमयरलवशषसह"
    )

    val alphabetKeys = alphabets.keys.toList()
    val length = (8..10).random()
    val pseudo = StringBuilder()
    var lastAlphabet: String? = null

    repeat(length) {
        val availableAlphabets = alphabetKeys.filter { it != lastAlphabet }
        val chosenAlphabet = availableAlphabets.random()
        val letters = alphabets[chosenAlphabet]!!
        val randomLetter = letters.random()

        pseudo.append(randomLetter)
        lastAlphabet = chosenAlphabet
    }

    return pseudo.toString()
}package com.example.espionapp

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun MainScreen() {
    var showPseudoDialog by remember { mutableStateOf(false) }
    var generatedPseudo by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // En-tête avec bouton MON PROFIL et drapeau
        HeaderSection()

        Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

        // Contenu principal avec colonne gauche et zones fonctionnelles
        Row(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        ) {
            // Colonne verticale gauche
            LeftColumn()

            // Zone centrale avec les 4 sections
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                // Zone MISSIONS
                MissionsSection()

                Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

                // Zone CANAL
                CanalSection()

                Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

                // Zone RÉSEAU (Carte du monde)
                ReseauSection()

                Divider(color = Color(0xFFE0E0E0), thickness = 1.dp)

                // Zone PISTER
                PisterSection(
                    onSearchClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://trends.google.com/trends/"))
                        context.startActivity(intent)
                    },
                    onPseudosClick = {
                        generatedPseudo = generateUniquePseudo()
                        showPseudoDialog = true
                    }
                )
            }
        }
    }

    // Dialog pour afficher le pseudo
    if (showPseudoDialog) {
        PseudoDialog(
            pseudo = generatedPseudo,
            onDismiss = { showPseudoDialog = false }
        )
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = { /* Action profil */ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF2C2C2C)
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            Text(
                text = "MON PROFIL",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 2.sp
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "🇫🇷",
            fontSize = 32.sp,
            modifier = Modifier
                .clickable { /* Changement de langue */ }
                .padding(end = 20.dp)
        )
    }
}

@Composable
fun LeftColumn() {
    Column(
        modifier = Modifier
            .width(38.dp)
            .fillMaxHeight()
            .background(Color(0xFFF8F9FA)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Section MISSIONS
        VerticalTextSection(
            text = "MISSIONS",
            modifier = Modifier.height(196.dp)
        )

        // Section CANAL
        VerticalTextSection(
            text = "CANAL",
            modifier = Modifier.height(249.dp)
        )

        // Section RÉSEAU
        VerticalTextSection(
            text = "RÉSEAU",
            modifier = Modifier.height(249.dp)
        )

        // Section PISTER
        VerticalTextSection(
            text = "PISTER",
            modifier = Modifier.height(106.dp)
        )
    }
}

@Composable
fun VerticalTextSection(text: String, modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clickable { }
            .background(Color(0xFFF8F9FA)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            text.forEach { char ->
                Text(
                    text = char.toString(),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF1A1A1A),
                    letterSpacing = 2.sp
                )
            }
        }
    }
}

@Composable
fun MissionsSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(196.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "(Zone de missions)",
            fontSize = 18.sp,
            color = Color(0xFF5F6368),
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun CanalSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(249.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "♂",
            fontSize = 80.sp,
            color = Color(0xFF4A90E2),
            modifier = Modifier.clickable { }
        )

        Spacer(modifier = Modifier.width(30.dp))

        Text(
            text = "VS",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF2C2C2C)
        )

        Spacer(modifier = Modifier.width(30.dp))

        Text(
            text = "♀",
            fontSize = 80.sp,
            color = Color(0xFFFF69B4),
            modifier = Modifier.clickable { }
        )
    }
}

@Composable
fun ReseauSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(249.dp)
            .background(Color.White)
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        // IMPORTANT : Utilise l'image de la carte du monde
        Image(
            painter = painterResource(id = R.drawable.world_map_detailed),
            contentDescription = "Carte du monde",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit
        )
    }
}

@Composable
fun PisterSection(onSearchClick: () -> Unit, onPseudosClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(106.dp)
            .background(Color.White)
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Bouton RECHERCHE
        ActionButton(
            text = "RECHERCHE",
            icon = "📊",
            onClick = onSearchClick
        )

        Spacer(modifier = Modifier.width(15.dp))

        // Bouton PSEUDOS
        ActionButton(
            text = "PSEUDOS",
            icon = "🔒",
            onClick = onPseudosClick
        )
    }
}

@Composable
fun ActionButton(text: String, icon: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFFF8F9FA)
        ),
        shape = RoundedCornerShape(10.dp),
        border = androidx.compose.foundation.BorderStroke(2.dp, Color(0xFFE0E0E0)),
        modifier = Modifier
            .width(189.dp)
            .height(57.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = icon,
                fontSize = 20.sp,
                modifier = Modifier.padding(end = 8.dp)
            )
            Text(
                text = text,
                fontSize = 13.sp,
                fontWeight = FontWeight.Black,
                color = Color(0xFF2C2C2C),
                letterSpacing = 1.sp
            )
        }
    }
}

@Composable
fun PseudoDialog(pseudo: String, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(15.dp),
            color = Color.White,
            modifier = Modifier.padding(20.dp)
        ) {
            Column(
                modifier = Modifier.padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "VOTRE PSEUDO UNIQUE",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF2C2C2C),
                    letterSpacing = 1.sp,
                    modifier = Modifier.padding(bottom = 20.dp)
                )

                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFFF8F9FA),
                    modifier = Modifier.padding(bottom = 20.dp)
                ) {
                    Text(
                        text = pseudo,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF4A90E2),
                        textAlign = TextAlign.Center,
                        letterSpacing = 2.sp,
                        modifier = Modifier.padding(20.dp)
                    )
                }

                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2C2C2C)
                    ),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = "FERMER",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

// Fonction pour générer un pseudo unique
fun generateUniquePseudo(): String {
    val alphabets = mapOf(
        "french" to "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ",
        "arabic" to "ابتثجحخدذرزسشصضطظعغفقكلمنهوي",
        "russian" to "абвгдежзийклмнопрстуфхцчшщъыьэюя",
        "chinese" to "的一是不了人我在有他这为之大来以个中上们到说国和地也子时道出而要于就下得可你年生自会那后能对着事其里所去行过家十用发天如然作方成者多日都三小军二无同么经法当起与好看学进种将还分此心前面又定见只主没公从",
        "hindi" to "अआइईउऊएऐओऔकखगघङचछजझञटठडढणतथदधनपफबभमयरलवशषसह"
    )

    val alphabetKeys = alphabets.keys.toList()
    val length = (8..10).random()
    val pseudo = StringBuilder()
    var lastAlphabet: String? = null

    repeat(length) {
        val availableAlphabets = alphabetKeys.filter { it != lastAlphabet }
        val chosenAlphabet = availableAlphabets.random()
        val letters = alphabets[chosenAlphabet]!!
        val randomLetter = letters.random()

        pseudo.append(randomLetter)
        lastAlphabet = chosenAlphabet
    }

    return pseudo.toString()
}
