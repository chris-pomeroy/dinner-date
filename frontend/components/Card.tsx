import {Image, Text, View} from 'react-native';
import {IconSymbol} from "@/components/ui/IconSymbol";
import React from "react";

export function Card() {

    return (
        <View style={{
            borderRadius: 20,
            overflow: "hidden",
            backgroundColor: "lightgrey",
            width: "90%",
            height: "90%"
        }}>
            <View style={{
                width: "100%",
                height: "80%",
                overflow: "hidden"
            }}>
                <Image
                    style={{
                        width: "100%",
                        height: "100%"
                    }}
                    source={require('@/assets/images/salmon-poke.jpg')} />
                <View style={{
                    position: "absolute",
                    bottom: 10,
                    left: 10
                }}>
                    <Text style={{
                        fontSize: 30,
                        fontWeight: "bold",
                        color: "white"
                    }}>Salmon Poke Bowl</Text>
                    <Text style={{color: "white"}}>A taste sensation you'll write home to your parents about</Text>
                </View>
            </View>
            <View style={{
                flexDirection: "row",
                alignItems: "center",
                justifyContent: "space-around",
                height: "20%"
            }}>
                <IconSymbol size={60} name="xmark.circle.fill" color="red" />
                <IconSymbol size={60} name="heart.fill" color="green" />
            </View>

        </View>
    );
}

