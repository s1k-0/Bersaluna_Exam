package com.example.bersaluna_application

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppContent()
        }
    }
}

//Porsche model
data class PorscheModel(
    val name: String,
    val imageResId: Int, //ID image
    val description: String,
    var isFavorite: Boolean = false
)

//Display a list of Porsche models
@Composable
fun PorscheList(porscheModels: List<PorscheModel>) {
    val listState = rememberLazyListState()

    Box(
        modifier = Modifier.fillMaxSize().background(Color.Black)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            state = listState
        ) {
            itemsIndexed(porscheModels) { index, model ->
                PorscheItem(model, index)
            }
        }
        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
            adapter = rememberScrollbarAdapter(listState)
        )
    }
}

//Display a single Porsche model item
@Composable
fun PorscheItem(model: PorscheModel, index: Int) {
    Column(modifier = Modifier.fillMaxWidth()) {
        // Display My title / Introduction of my APP
        if (index == 0) {
            Text(
                text = "Ivan's Porsche Dream",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // Image of the Porsche model
        Image(
            painter = painterResource(id = model.imageResId),
            contentDescription = model.name,
            modifier = Modifier.fillMaxWidth().height(200.dp),
            contentScale = ContentScale.Crop
        )

        // Display the name and description of the Porsche model
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally // Center horizontally
        ) {
            Text(
                text = model.name,
                textAlign = TextAlign.Center,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = model.description,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

// Preview of the PorscheList
@Preview
@Composable
fun AppContent() {
    val porscheModels = getPorscheModels()
    PorscheList(porscheModels = porscheModels)
}

@Composable
fun PreviewPorscheList() {
    val porscheModels = getPorscheModels()
    PorscheList(porscheModels = porscheModels)
}

// list of Porsche models
fun getPorscheModels(): List<PorscheModel> {
    return listOf(
        PorscheModel(
            "------------------------------ 911 --------------------------------",
            R.drawable.porsche_911,
            "The Porsche 911 is a classic sports car known for its timeless design and performance."
        ),
        PorscheModel(
            "---------------------------- Cayenne -----------------------------",
            R.drawable.porsche_cayenne,
            "The Porsche Cayenne is a luxury SUV with a sporty character and powerful engines."
        ),
        PorscheModel(
            "---------------------------- Panamera --------------------------",
            R.drawable.porsche_panamera,
            "The Porsche Panamera is a high-performance luxury sedan with four doors and seating for four."
        ),
        PorscheModel(
            "---------------------------- Boxster --------------------------",
            R.drawable.porsche_boxster,
            "The Porsche Boxster is a two-seater convertible sports car."
        ),
        PorscheModel(
            "---------------------------- Macan --------------------------",
            R.drawable.porsche_macan,
            "The Porsche Macan is a compact luxury SUV with sporty performance."
        )
    )
}
