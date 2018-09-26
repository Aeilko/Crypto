package assignments;

import java.util.TreeMap;

import ciphers.ShiftCipher;
import ciphers.VigenereCipher;
import utils.FrequencyAnalysis;

public class Assignment1 {
	public static String ciphertext = "gvt pmefwgn ohyt xs i zrrotmeeoceia fsg iv jrgieza ngoa. bur pddg bs kgtme vg pn mkgstsqba cu tpr vbjiia bqtav gufuuou gvt sbenwz on ubfbuh. gus kazyvsht mivrknkr bt wuuna dxearaqt ov crfyiia tiaf qfyotda qnhts jnpy zo uvqrae xnysulqguwr avq pctsqfg cu sbbas zowyf rxskbisxel ng etspz vgravq. gvt wweyr'y otqrgi kvbjb iidvywoabvbb jedryceel nyctg bur dtravnb muts nbs swhgvkrv zrgdpwgnaoa. bur dtravnb muts nbs ibf pcgsbny ogeif nfk tpr jcgll'f yoxgmfg gxnoyr guuzpr cu czhqs uit, nar getngsj ivqhgirqrf rumqanht tpr esmiwa. fouavvlo uit svsad, bur kurtq'f zprorfh ufnfucge wvytoetq, vg aokngsj iv gus eezfvot gcys. zpror toy fqaqg wadr nzyo jrrb balr, jwzh yngog avq vfgn aunfxno n twgnb svsad ipecys bur htrzvgcxiiy zssiia ywte. cfvbv tpvf ugs, yngog hif oiolb hc o hujfgottqny zxqcrswkd vngigat tng gnl crhgokuraociy vbsuagem. zhm crfhiia tirf qf ucbe bb zoty aznza iaynbjs. jnufpiv, na wyliaq wc tpr csxsqna ujln, vf wzsmys o eezfvot gcys ogaj fgoze. orbugaxuvqgltl gvt bqttsyt qfyocd qa gvk pmefwpn ohyt os yrfvb iaynbj lwpnhtd qa gvk sbenwi on ubfsuh nar qetbauono gb wgav. bgvkr avtbxfqpnbz iaynbss qa gvk pmefwpn ohyt onkyhrt gzrnhkr bhap, aeafrf zuvo nbs kqfu ojmqavgiezrq pe izna, pjbqlnb gduvawhtmerr hy shjoxt, bnecz alzvbxsbresj bg fnisi ienpoa, iaq rplun nrsivvfhtrmq om aam. va ftcmag mkazf, gvtrm ung glab ostn iqqwziwa bt prbvswiiiy vgaavqf, cltma pftabrq pe azno giabrf gacp nf ipe nbe qumureqxat eroyovf bf ps bbhfosb ergdrbf. csxsqna ujln vfzgnlf nft ongrb glab uwhtwevqgltl fwvnqsvqgnb, unjxno orst uarq wc tpr coyt jl pcaovvnz voereg huku ng zhm cbfiuohrgk avq gvt bzvgwyh qa gvtiz geoje we ng pcyhvgotqbag uoz gusor mzcwgea.";

	public static void main(String[] args) {
		int keyLength = 12;
		String[] parts = FrequencyAnalysis.splitter(keyLength, ciphertext);
		String key = "";

		for(int i = 0; i < keyLength; i++){
			TreeMap<Character, Integer> freq = FrequencyAnalysis.letter(parts[i]);
			TreeMap<Integer, Double> distance = FrequencyAnalysis.statisticalDistance(freq);
			int shift = FrequencyAnalysis.lowestVal(distance);
			char k = ShiftCipher.intToChar(shift);
			key = key + k;
		}
		
		System.out.println("Key " + key);
		
		VigenereCipher cipher = new VigenereCipher(key);
		System.out.println(cipher.decrypt(ciphertext));
	}
}