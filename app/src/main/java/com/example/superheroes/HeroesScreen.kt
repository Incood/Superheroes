package com.example.superheroes

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.model.Hero
import com.example.superheroes.model.HeroesRepository.heroes
import com.example.superheroes.ui.theme.SuperheroesTheme

class HeroesScreen {
    @Composable
    fun HeroApp() {
        Scaffold(
            topBar = {
                HeroTopAppBar()
            }
        ) { it ->
            LazyColumn(
                contentPadding = it
            ) {
                items(heroes) {
                    HeroItem(
                        hero = it,
                        modifier = Modifier.padding(
                            start = dimensionResource(id = R.dimen.padding_small),
                            end = dimensionResource(id = R.dimen.padding_small),
                            top = dimensionResource(id = R.dimen.padding_medium),
                            bottom = dimensionResource(id = R.dimen.padding_medium)
                        )
                    )
                }
            }
        }
    }

    @Composable
    fun HeroItem(
        hero: Hero,
        modifier: Modifier = Modifier
    ) {
        Card(
            modifier = modifier
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(R.dimen.padding_small))
                    .sizeIn(minHeight = 72.dp)
            ) {
                HeroIcon(imageRes = hero.imageRes)
                HeroInformation(hero.nameRes, hero.descriptionRes)
            }
        }
    }

    @Composable
    fun HeroInformation(
        @StringRes heroName: Int,
        @StringRes descriptionRes: Int,
        modifier: Modifier = Modifier
    ) {
        Column {
            Text(
                text = stringResource(id = heroName),
                style = MaterialTheme.typography.displaySmall,
                modifier = Modifier.padding( top = dimensionResource(id = R.dimen.padding_small))

            )
            Text(
                text = stringResource(id = descriptionRes),
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                softWrap = true,
                modifier = Modifier
            )
        }
    }

    @Composable
    fun HeroIcon(
        @DrawableRes imageRes: Int,
        modifier: Modifier = Modifier
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.padding_small))
                .size(dimensionResource(id = R.dimen.image_size))
                .clip(MaterialTheme.shapes.small)
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        SuperheroesTheme {
            HeroItem(hero = heroes[3])
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Preview() {
        SuperheroesTheme {
            HeroItem(hero = heroes[0])
        }
    }
}
