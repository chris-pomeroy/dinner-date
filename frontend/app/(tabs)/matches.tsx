import {SafeAreaView} from 'react-native';
import {useState} from "react";
import Toggle from "@/components/Toggle";
import MatchesList from "@/components/MatchesList";
import LikesList from "@/components/LikesList";

export default function MatchesScreen() {

    const [isSwitched, setIsSwitched] = useState(false);

    return (
        <SafeAreaView>
            <Toggle
                isSwitched={isSwitched}
                setIsSwitched={setIsSwitched}
            />
            {isSwitched ? <LikesList /> : <MatchesList />}
        </SafeAreaView>
    );
}
